package com.damidev.dd;

import android.app.Application;
import android.content.Context;
import android.content.res.Resources;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Lukas Janoska
 */
@Module
public class AppModule {

    private final DDApplication application;
    private final Context context;

    public AppModule(final DDApplication application) {
        this.application = application;
        this.context = application.getApplicationContext();
    }

    @Provides
    @Singleton
    Application provideApplication () {
        return this.application;
    }

    @Provides
    @Singleton
    Context provideContext () {
        return this.context;
    }

    @Provides
    @Singleton
    Resources provideResources() {
        return application.getResources();
    }
}
