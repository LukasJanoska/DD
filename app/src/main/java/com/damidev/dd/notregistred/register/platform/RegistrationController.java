package com.damidev.dd.notregistred.register.platform;

import com.damidev.dd.shared.dataaccess.RegistrationRestService;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;

import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import retrofit2.Response;

public class RegistrationController {

    private RegistrationRestService restService;

    public RegistrationController(RegistrationRestService restService) {
        this.restService = restService;
    }

    public Flowable<Response<ServerRegResultDto>> registration(final String username, final String password) {
        return restService
                .registration(username, password)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Response<ServerRegResultDto>> fbRegistration(final String username, final String fID) {
        return restService
                .fbRegistration(username, fID)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Response<ServerRegResultDto>> login(final String username, final String password) {
        return restService
                .login(username, password)
                .observeOn(AndroidSchedulers.mainThread());
    }

    public Flowable<Response<ServerRegResultDto>> fbLogin(final String username, final String fID) {
        return restService
                .fbLogin(username, fID)
                .observeOn(AndroidSchedulers.mainThread());
    }

}
