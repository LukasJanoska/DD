package com.damidev.dd.main.account.profile.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.DatabaseProfileHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseProfileHandler provideProfileHandler(Context context) {
        return new DatabaseProfileHandler(context);
    }
}