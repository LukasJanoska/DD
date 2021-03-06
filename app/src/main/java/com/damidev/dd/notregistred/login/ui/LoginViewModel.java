package com.damidev.dd.notregistred.login.ui;


import android.content.Context;
import android.databinding.ObservableField;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.core.retain.RetainFragmentHelper;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;
import com.damidev.dd.notregistred.register.platform.RegistrationController;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.shared.utils.Utils;
import com.google.gson.Gson;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;

import static com.facebook.FacebookSdk.getApplicationContext;

public class LoginViewModel extends BaseViewModel<LoginView> {

    private static final String TAG_RETAIN_FRAGMENT = "retainFragment";

    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> password = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> usernameError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordError = new ObservableField<CharSequence>();

    private RegistrationController registrationController;
    private Context context;
    private FragmentManager fragmentManager;
    private DatabaseHandler db;

    private AsyncProcessor<Response<ServerRegResultDto>> registrationProcessor;
    private Disposable registrationDisposable;

    private boolean restored;

    @Inject
    public LoginViewModel(Context context, final FragmentManager fragmentManager, final RegistrationController registrationController, DatabaseHandler db ) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.registrationController = registrationController;
        this.db = db;
    }

    public void onClickReg(final View view) {
        getView().replaceWithRegFragment();
    }

    public void onClickLogin(final View view) {
        // validation missing

        if(!validateInputs()) {
            return;
        }

        ConnectivityManager connectivityManager = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifiNetInfo = connectivityManager.getActiveNetworkInfo();

        if(!Utils.isInternetAvailable(wifiNetInfo)) {
            getView().showErrorDialog("No internet");
            return;
        }

        getView().showProgressDialog();

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = getOrCreateObservable(null,  email.get().toString(), password.get().toString());

        flowable.subscribe(registrationProcessor);
    }

    public void onClickFBLogin(String email, String fID) {
        getView().showProgressDialog();//

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = getOrCreateFBObservable(null,  email, fID);

        flowable.subscribe(registrationProcessor);
    }

    public boolean validateInputs() {
        boolean valid = true;
        usernameError.set(null);
        passwordError.set(null);

        if(TextUtils.isEmpty(email.get())) {
            usernameError.set("email required");
            valid = false;
        } else if(!(Utils.isEmailValid(email.get().toString()))) {
            usernameError.set("email not valid");
            valid = false;
        } else {
            usernameError.set(null);
        }

        if(TextUtils.isEmpty(password.get())) {
            passwordError.set("zadejte heslo");
            valid = false;
        } else {
            passwordError.set(null);
        }

        return valid;
    }

    @Override
    public void viewResumed() {
        super.viewResumed();

        if (registrationDisposable != null && !restored) {
            registrationProcessor.subscribe(new RegistrationSubscriber());
        }
    }

    @Override
    public void attachView(@NonNull LoginView view) {
        super.attachView(view);

        if(RetainFragmentHelper.isNotNull(TAG_RETAIN_FRAGMENT, fragmentManager)) {
            final Flowable<Response<ServerRegResultDto>> flowable =
                    RetainFragmentHelper.getObjectOrNull(TAG_RETAIN_FRAGMENT, fragmentManager);

            if(registrationProcessor == null) {
                this.registrationProcessor = AsyncProcessor.create();
                this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

                flowable.subscribe(registrationProcessor);
                restored = true;
            }
        }
    }

    @Override
    public void detachView() {
        super.detachView();

        if (registrationDisposable != null) {
            registrationDisposable.dispose();
        }
    }

    public ObservableField<CharSequence> getEmail() {
        return email;
    }
    public ObservableField<CharSequence> getPassword() {
        return password;
    }
    public ObservableField<CharSequence> getUsernameError() {
        return usernameError;
    }
    public ObservableField<CharSequence> getPasswordError() {
        return passwordError;
    }

    private class RegistrationSubscriber extends DisposableSubscriber<Response<ServerRegResultDto>> {

        @Override
        public void onNext(Response<ServerRegResultDto> response) {
            String s = "";

            ServerRegResultDto data = response.body();
            if(response.code()==200) {
                if(data!=null) {
                    if(data.getResponseCode()==1) {
                        Profile profil = new Profile();

                        Gson gson = new Gson();
                        String jsonFavourites = gson.toJson(data.getChildResponse().getFavorites());

                        profil.set_id(data.getChildResponse().getId());
                        profil.set_email(data.getChildResponse().getEmail());
                        profil.set_name(data.getChildResponse().getName());
                        profil.set_last_name(data.getChildResponse().getLastname());
                        profil.set_rights(data.getChildResponse().getRights());
                        profil.set_phone(data.getChildResponse().getPhone());
                        profil.set_description(data.getChildResponse().getDescription());
                        profil.set_token(data.getChildResponse().getToken());
                        profil.set_map(jsonFavourites);
                        db.addProfile(profil);

                        getView().startMainActivity(profil.get_id(), profil.get_token());
                    } /*else if (data.getResponseCode()==7) { // takto to malo fungovat podla zadania, ale nefunguje, pri responsecode inom ako 1 navracia server 200, takze mi nevyplni body
                        getView().showErrorDialog("incorrect username or password");
                    } */
                } else {
                    getView().showErrorDialog("error");
                }
            } else {
                //getView().showErrorDialog(response.errorBody().string());
                getView().showErrorDialog("Please do registration");
            }
        }

        @Override
        public void onError(Throwable t) {
            if(getView() != null) {
                getView().hideProgressDialog();
                getView().showErrorDialog(t.getMessage());
            }
            clearRetainFragment();
        }

        @Override
        public void onComplete() {
            if(getView() != null) {
                getView().hideProgressDialog();
            }
            clearRetainFragment();
        }
    }

    @NonNull
    private Flowable<Response<ServerRegResultDto>> getOrCreateObservable(Bundle savedInstanceState,
                                                                         final String username, final String password) {
        Flowable<Response<ServerRegResultDto>> flowable;

        if (savedInstanceState == null) {
            // first run, create and set observable
            flowable = createAndSetFlowable(username, password);
        } else {
            // following runs, get observable from retained fragment
            flowable = RetainFragmentHelper.getObjectOrNull(TAG_RETAIN_FRAGMENT, fragmentManager);
            // fragment may be removed during memory clean up, if so, create and set observable again
            if (flowable == null) {
                flowable = createAndSetFlowable(username, password);
            }
        }

        return flowable;
    }

    @NonNull
    private Flowable<Response<ServerRegResultDto>> getOrCreateFBObservable(Bundle savedInstanceState,
                                                                         final String username, final String fID) {
        Flowable<Response<ServerRegResultDto>> flowable;

        if (savedInstanceState == null) {
            // first run, create and set observable
            flowable = createAndSetFBFlowable(username, fID);
        } else {
            // following runs, get observable from retained fragment
            flowable = RetainFragmentHelper.getObjectOrNull(TAG_RETAIN_FRAGMENT, fragmentManager);
            // fragment may be removed during memory clean up, if so, create and set observable again
            if (flowable == null) {
                flowable = createAndSetFBFlowable(username, fID);
            }
        }

        return flowable;
    }

    @NonNull
    private Flowable<Response<ServerRegResultDto>> createAndSetFlowable(final String username,
                                                                        final String password) {

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = this.registrationController
                .login(username, password)
                .cache();

        RetainFragmentHelper.setObject(TAG_RETAIN_FRAGMENT, fragmentManager, flowable);

        return flowable;
    }

    @NonNull
    private Flowable<Response<ServerRegResultDto>> createAndSetFBFlowable(final String username,
                                                                        final String fID) {

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = this.registrationController
                .fbLogin(username, fID)
                .cache();

        RetainFragmentHelper.setObject(TAG_RETAIN_FRAGMENT, fragmentManager, flowable);

        return flowable;
    }

    public void clearRetainFragment() {
        RetainFragmentHelper.clear(TAG_RETAIN_FRAGMENT, fragmentManager);
        registrationDisposable = null;
        registrationProcessor = null;
    }
}
