package com.damidev.dd.notregistred.login.platform;

import com.damidev.dd.notregistred.login.dataaccess.RegistrationRestService;
import com.damidev.dd.notregistred.login.dataaccess.ServerRegResultDto;

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


//        return Observable.create(new ObservableOnSubscribe<Boolean>() {
//            @Override
//            public void subscribe(ObservableEmitter<Boolean> emmiter) throws Exception {
//                try {
//                    Thread.sleep(1_000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//
//                authenticationRestService.doLogin(username, password);
//
//                sessionController.create();
//
//                emmiter.onNext(true);
//                emmiter.onComplete();
//            }
//        })
//        .subscribeOn(Schedulers.io())
//
//        .toFlowable(BackpressureStrategy.LATEST);
    }

}
