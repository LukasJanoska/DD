package com.damidev.dd.notregistred.register.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.notregistred.register.ui.RegFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = RegModule.class
)
public interface RegComponent extends D2Component<RegFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<RegModule, RegComponent> {
    }
}
