/*
package com.damidev.dd.main.ui;

import android.content.Context;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.View;

import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.core.mvvm.rx.RxRetainBaseViewModel;
import com.damidev.core.retain.RetainFragmentHelper;
import com.damidev.dd.registration.dataaccess.RegistrationResultDto;
import com.damidev.dd.registration.platform.RegistrationController;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.processors.AsyncProcessor;
import io.reactivex.subscribers.DisposableSubscriber;
import retrofit2.Response;


@ActivityScope
public class MainViewModel extends RxRetainBaseViewModel<MainView> {

    private static final String TAG_RETAIN_FRAGMENT = "retainFragment";

    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> password = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> usernameError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordError = new ObservableField<CharSequence>();

    private RegistrationController registrationController;
    private Context context;
    private FragmentManager fragmentManager;

    private AsyncProcessor<Response<RegistrationResultDto>> registrationProcessor;
    private Disposable registrationDisposable;

    private boolean restored;

    @Inject
    public MainViewModel(Context context, final FragmentManager fragmentManager, final RegistrationController registrationController ) {
        this.context = context;
        this.fragmentManager = fragmentManager;
        this.registrationController = registrationController;
    }

    public void onClickLogin(final View view) {
        // validation missing

        if(!validateInputs()) {
            return;
        }

//        if(!networkController.isNetworkConnectionAvailable()) {
//            getView().showErrorToast(context.getString(R.string.error_no_internet_connection));
//            return;
//        }

        getView().showProgressDialog();

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<RegistrationResultDto>> flowable = getOrCreateObservable(null,  email.get().toString(), password.get().toString()); //email.get().toString(), password.get().toString());

        flowable.subscribe(registrationProcessor);
    }

    public boolean validateInputs() {
        boolean valid = true;

        if(TextUtils.isEmpty(email.get())) {
            usernameError.set("zadejte email");
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
    public void attachView(@NonNull MainView view) {
        super.attachView(view);

        if(RetainFragmentHelper.isNotNull(TAG_RETAIN_FRAGMENT, fragmentManager)) {
            final Flowable<Response<RegistrationResultDto>> flowable =
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

    private class RegistrationSubscriber extends DisposableSubscriber<Response<RegistrationResultDto>> {

        @Override
        public void onNext(Response<RegistrationResultDto> response) {
            String s = "";

            RegistrationResultDto data;
            data = response.body();
            //navigatorController.startActivity(MainActivity.class);
            //navigatorController.finishActivity();
        }

        @Override
        public void onError(Throwable t) {
            if(getView() != null) {
                getView().hideProgressDialog();
                getView().showErrorDialog(t.getMessage());
            }

            //navigatorController.startActivity(MainActivity.class);
            //navigatorController.finishActivity();
        }

        @Override
        public void onComplete() {
            if(getView() != null) {
                getView().hideProgressDialog();
            }
        }
    }

    @NonNull
    private Flowable<Response<RegistrationResultDto>> getOrCreateObservable(Bundle savedInstanceState,
                                                                            final String username, final String password) {
        Flowable<Response<RegistrationResultDto>> flowable;

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
    private Flowable<Response<RegistrationResultDto>> createAndSetFlowable(final String username,
                                                          final String password) {

        this.registrationProcessor = AsyncProcessor.create();
        this.registrationDisposable = registrationProcessor.subscribeWith(new RegistrationSubscriber());

        final Flowable<Response<RegistrationResultDto>> flowable = this.registrationController
                .registration(username, password)
                .cache();

        RetainFragmentHelper.setObject(TAG_RETAIN_FRAGMENT, fragmentManager, flowable);

        return flowable;
    }
}
*/
