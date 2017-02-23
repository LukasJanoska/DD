package com.damidev.dd.notregistred.base.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.shared.inject.ActivityModule;

import dagger.Subcomponent;

@ActivityScope
@Subcomponent(
        modules = { NotRegistredModule.class, ActivityModule.class }
)
public interface NotRegistredComponent extends D2Component<NotRegistredActivity> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<NotRegistredModule, NotRegistredComponent> {
        NotRegistredComponent.Builder activityModule(ActivityModule activityModule);
    }
}
