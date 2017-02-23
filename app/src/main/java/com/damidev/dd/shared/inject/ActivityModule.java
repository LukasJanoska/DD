package com.damidev.dd.shared.inject;

import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.damidev.core.inject.scope.ActivityScope;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * @author Lukas Janoska
 */
@Module
public class ActivityModule {

    private final WeakReference<AppCompatActivity> activity;

    public ActivityModule(AppCompatActivity activity) {
        this.activity = new WeakReference<AppCompatActivity>(activity);
    }

    @Provides
    @ActivityScope
    FragmentManager provideFragmentManager() {
        return activity.get().getSupportFragmentManager();
    }

}