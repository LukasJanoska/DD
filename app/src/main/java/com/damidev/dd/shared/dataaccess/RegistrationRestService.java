package com.damidev.dd.shared.dataaccess;

import android.support.annotation.WorkerThread;

import com.damidev.core.inject.scope.FragmentScope;

import javax.inject.Inject;

import io.reactivex.Flowable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

@FragmentScope
public class RegistrationRestService {

    DamiRestApi restApi;

    @Inject
    public RegistrationRestService(final DamiRestApi restApi) {
        this.restApi = restApi;
    }

    @WorkerThread
    public Flowable<Response<ServerRegResultDto>> registration(final String username, final String password) {

        return restApi.registration(username, password)
                .subscribeOn(Schedulers.io());
    }

    @WorkerThread
    public Flowable<Response<ServerRegResultDto>> login(final String username, final String password) {

        return restApi.login(username, password)
                .subscribeOn(Schedulers.io());
    }
}
