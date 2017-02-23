/*
package com.damidev.dd.main.inject;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.dd.registration.dataaccess.RegistrationRestApi;
import com.damidev.dd.registration.dataaccess.RegistrationRestService;
import com.damidev.dd.registration.platform.RegistrationController;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;


@Module
public class MainModule implements D2Module {

    @Provides
    @ActivityScope
    public RegistrationController provideController(RegistrationRestService restService) {
        return new RegistrationController(restService);
    }

    @Provides
    @ActivityScope
    public RegistrationRestApi provideRestApi(final Retrofit retrofit) {
        return retrofit.create(RegistrationRestApi.class);
    }

}
*/
