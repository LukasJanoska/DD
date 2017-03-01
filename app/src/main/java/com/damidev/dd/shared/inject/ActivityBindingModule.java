package com.damidev.dd.shared.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.InjectKey;
import com.damidev.dd.main.base.inject.MainComponent;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.notregistred.base.inject.NotRegistredComponent;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.splashscreen.inject.SplashScreenComponent;
import com.damidev.dd.splashscreen.ui.SplashScreenActivity;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module(
        subcomponents = {
                NotRegistredComponent.class,
                SplashScreenComponent.class,
                MainComponent.class
        }
)
public abstract class ActivityBindingModule {

    @Binds
    @IntoMap
    @InjectKey(NotRegistredActivity.class)
    public abstract ComponentBuilder registrationComponentBuilder(NotRegistredComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(SplashScreenActivity.class)
    public abstract ComponentBuilder splashScreenComponentBuilder(SplashScreenComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(MainActivity.class)
    public abstract ComponentBuilder mainComponentBuilder(MainComponent.Builder builder);

}