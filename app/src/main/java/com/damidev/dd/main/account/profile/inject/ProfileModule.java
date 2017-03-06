package com.damidev.dd.main.account.profile.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class ProfileModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseHandler provideHandler(Context context) {
        return new DatabaseHandler(context);
    }
}
