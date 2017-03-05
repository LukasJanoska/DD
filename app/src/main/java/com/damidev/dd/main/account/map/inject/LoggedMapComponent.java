package com.damidev.dd.main.account.map.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.map.ui.LoggedMapFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = LoggedMapModule.class
)
public interface LoggedMapComponent extends D2Component<LoggedMapFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<LoggedMapModule, LoggedMapComponent> {
    }
}
