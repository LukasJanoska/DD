package com.damidev.dd.notregistred.register.ui;


import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.core.retain.RetainFragmentHelper;
import com.damidev.dd.main.account.profileedit.platform.DatabaseProfileHandler;
import com.damidev.dd.notregistred.register.platform.RegistrationController;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.shared.utils.Utils;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;


public class RegViewModel extends BaseViewModel<RegView> {

    private static final String TAG_RETAIN_FRAGMENT = "retainFragment";

    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> password1 = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> password2 = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> emailError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordError1 = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordError2 = new ObservableField<CharSequence>();

    public ObservableField<CharSequence> getEmail() {
        return email;
    }

    public ObservableField<CharSequence> getPassword1() {
        return password1;
    }

    public ObservableField<CharSequence> getPassword2() {
        return password2;
    }

    public ObservableField<CharSequence> getEmailError() {
        return emailError;
    }

    public ObservableField<CharSequence> getPasswordError1() {
        return passwordError1;
    }

    public ObservableField<CharSequence> getPasswordError2() {
        return passwordError2;
    }

    private RegistrationController registrationController;
    private Context context;
    private FragmentManager fragmentManager;
    private DatabaseProfileHandler db;

    private AsyncProcessor<Response<ServerRegResultDto>> registrationProcessor;
    private Disposable registrationDisposable;

    private boolean restored;

    @Inject
    public RegViewModel(Context context, final FragmentManager fragmentManager, final RegistrationController registrationController, DatabaseProfileHandler db) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.registrationController = registrationController;
        this.db = db;
    }

    public void onClickRegister(final View view) {
        // validation missing

        if(!validateInputs()) {
            return;
        }

        getView().showProgressDialog();//

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = getOrCreateObservable(null,  email.get().toString(), password2.get().toString());

        flowable.subscribe(registrationProcessor);
    }

    public void onClickFBRegister(String email, String fID, String picture) {

        getView().showProgressDialog();//

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = getOrCreateFBObservable(null,  email , fID);

        flowable.subscribe(registrationProcessor);
    }

    public boolean validateInputs() {
        boolean valid = true;
        emailError.set(null);
        passwordError1.set(null);
        passwordError2.set(null);

        if(TextUtils.isEmpty(email.get())) {
            emailError.set("email required");
            valid = false;
        } else if(!(Utils.isEmailValid(email.get().toString()))) {
            emailError.set("email not valid");
            valid = false;
        } else {
            emailError.set(null);
        }

        if(TextUtils.isEmpty(password1.get())) {
            passwordError1.set("enter password");
            valid = false;
        } else {
            passwordError1.set(null);
        }

        if(TextUtils.isEmpty(password2.get())) {
            passwordError2.set("enter password");
            valid = false;
        } else if(!((password1.get()).equals(password2.get()))) {
            passwordError2.set("no password match");
            valid = false;
        } else {
            passwordError2.set(null);
        }

        return valid;
    }

    @Override
    public void viewResumed() {
        super.viewResumed();

        if (registrationDisposable != null && !restored) {
            registrationProcessor.subscribe(new RegViewModel.RegistrationSubscriber());
        }
    }

    @Override
    public void attachView(@NonNull RegView view) {
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

    private class RegistrationSubscriber extends DisposableSubscriber<Response<ServerRegResultDto>> {

        @Override
        public void onNext(Response<ServerRegResultDto> response) {
            String s = "";

            ServerRegResultDto data = response.body();
            if(response.code()==200) {
                if(data!=null) {
                    if(data.getResponseCode()==1) {
                        Profile profil = new Profile();
                        profil.set_id(data.getChildResponse().getId());
                        profil.set_email(data.getChildResponse().getEmail());
                        profil.set_name(data.getChildResponse().getName());
                        profil.set_last_name(data.getChildResponse().getLastname());
                        profil.set_rights(data.getChildResponse().getRights());
                        profil.set_phone(data.getChildResponse().getPhone());
                        profil.set_description(data.getChildResponse().getDescription());
                        profil.set_token(data.getChildResponse().getToken());
                        db.addProfile(profil);

                        getView().startMainActivity(profil.get_id(), profil.get_token());
                    } /*else if (data.getResponseCode()==7) { // takto to malo fungovat podla zadania, ale nefunguje, pri responsecode inom ako 1 navracia server 200, takze mi nevyplni body
                        getView().showErrorDialog("incorrect username or password");
                    } */
                } else {
                    getView().showErrorDialog("error");
                }
            } else {
                //getView().startNotRegistredActivity();
                //getView().showErrorDialog("email already registred, please do login");

            }
        }

        @Override
        public void onError(Throwable t) {
            if(getView() != null) {
                getView().hideProgressDialog();
                getView().showErrorDialog(t.getMessage());
            }
        }

        @Override
        public void onComplete() {
            if(getView() != null) {
                getView().hideProgressDialog();
            }
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
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegViewModel.RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = this.registrationController
                .registration(username, password)
                .cache();

        RetainFragmentHelper.setObject(TAG_RETAIN_FRAGMENT, fragmentManager, flowable);

        return flowable;
    }

    @NonNull
    private Flowable<Response<ServerRegResultDto>> createAndSetFBFlowable(final String username,
                                                                        final String fID) {

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegViewModel.RegistrationSubscriber());

        final Flowable<Response<ServerRegResultDto>> flowable = this.registrationController
                .fbRegistration(username, fID)
                .cache();

        RetainFragmentHelper.setObject(TAG_RETAIN_FRAGMENT, fragmentManager, flowable);

        return flowable;
    }
}
