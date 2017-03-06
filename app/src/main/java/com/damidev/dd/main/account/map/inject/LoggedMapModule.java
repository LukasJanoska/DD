package com.damidev.dd.main.account.map.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class LoggedMapModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseHandler provideProfileHandler(Context context) {
        return new DatabaseHandler(context);
    }
}
