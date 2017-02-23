package com.damidev.dd.notregistred.login.dataaccess;

import android.support.annotation.WorkerThread;

import com.damidev.core.inject.scope.FragmentScope;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@FragmentScope
public class RegistrationRestService {

    RegistrationRestApi restApi;

    @Inject
    public RegistrationRestService(final RegistrationRestApi restApi) {
        this.restApi = restApi;
    }

    @WorkerThread
    public Flowable<Response<ServerRegResultDto>> registration(final String username, final String password) {

//        try {
        return restApi.registration(username, password)
                .subscribeOn(Schedulers.io());

//            if (response.isSuccessful() && response.body() != null) {
//                if(response.body().isStatus()) {
//                    Timber.d("Ok");
//                    return true;
//                } else {
//                    Timber.d("Login failed: %s", response.body().getMsg());
//                    return false;
//                }
//            } else {
//                Timber.d("Login failed: %s", response.errorBody().toString());
//                return false;
//            }
//        } catch (Throwable e) {
//            Timber.d(e, "Login failed");
//            return false;
//        }
    }
}
