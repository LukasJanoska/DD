package com.damidev.dd.splashscreen.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.splashscreen.ui.SplashScreenActivity;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = { SplashScreenModule.class, ActivityModule.class }
)
public interface SplashScreenComponent extends D2Component<SplashScreenActivity> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<SplashScreenModule, SplashScreenComponent> {
        SplashScreenComponent.Builder activityModule(ActivityModule activityModule);
    }
}