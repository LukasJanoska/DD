package com.damidev.dd.notregistred.login.platform;

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

}
