package com.damidev.dd.main.base.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.shared.inject.ActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = { MainModule.class, ActivityModule.class }
)
public interface MainComponent extends D2Component<MainActivity> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<MainModule, MainComponent> {
        MainComponent.Builder activityModule(ActivityModule activityModule);
    }
}
