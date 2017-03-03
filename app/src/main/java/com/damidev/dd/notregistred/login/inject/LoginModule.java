package com.damidev.dd.notregistred.login.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.shared.dataaccess.DamiRestApi;
import com.damidev.dd.shared.dataaccess.RegistrationRestService;
import com.damidev.dd.main.account.profileedit.platform.DatabaseProfileHandler;
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
    public DatabaseProfileHandler provideProfileHandler(Context context) {
        return new DatabaseProfileHandler(context);
    }

    @Provides
    @FragmentScope
    public DamiRestApi provideRestApi(final Retrofit retrofit) {
        return retrofit.create(DamiRestApi.class);
    }
}
