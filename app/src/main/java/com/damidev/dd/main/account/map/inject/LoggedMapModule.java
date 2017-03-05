package com.damidev.dd.main.account.map.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.profileedit.platform.DatabaseProfileHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class LoggedMapModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseProfileHandler provideProfileHandler(Context context) {
        return new DatabaseProfileHandler(context);
    }
}
