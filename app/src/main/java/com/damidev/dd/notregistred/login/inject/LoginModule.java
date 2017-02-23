package com.damidev.dd.notregistred.login.inject;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.notregistred.login.dataaccess.RegistrationRestApi;
import com.damidev.dd.notregistred.login.dataaccess.RegistrationRestService;
import com.damidev.dd.notregistred.login.platform.RegistrationController;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class LoginModule implements D2Module {

    @Provides
    @FragmentScope
    public RegistrationController provideController(RegistrationRestService restService) {
        return new RegistrationController(restService);
    }

    @Provides
    @FragmentScope
    public RegistrationRestApi provideRestApi(final Retrofit retrofit) {
        return retrofit.create(RegistrationRestApi.class);
    }
}
