package com.damidev.dd.notregistred.register.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;
import com.damidev.dd.notregistred.register.platform.RegistrationController;
import com.damidev.dd.shared.dataaccess.DamiRestApi;
import com.damidev.dd.shared.dataaccess.RegistrationRestService;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class RegModule implements D2Module {

    @Provides
    @FragmentScope
    public RegistrationController provideController(RegistrationRestService restService) {
        return new RegistrationController(restService);
    }

    @Provides
    @FragmentScope
    public DatabaseHandler provideHandler(Context context) {
        return new DatabaseHandler(context);
    }

    @Provides
    @FragmentScope
    public DamiRestApi provideRestApi(final Retrofit retrofit) {
        return retrofit.create(DamiRestApi.class);
    }
}
