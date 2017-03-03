package com.damidev.dd.main.account.profileedit.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.profileedit.platform.ProfileCommunicator;
import com.damidev.dd.notregistred.login.platform.DatabaseProfileHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileEditModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseProfileHandler provideProfileHandler(Context context) {
        return new DatabaseProfileHandler(context);
    }

    @Provides
    @FragmentScope
    public ProfileCommunicator provideProfileComunicator() {
        return new ProfileCommunicator();
    }
}
