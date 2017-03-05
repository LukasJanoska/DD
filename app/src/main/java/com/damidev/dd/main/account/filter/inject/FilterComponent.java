package com.damidev.dd.main.account.filter.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.filter.ui.FilterFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = FilterModule.class
)
public interface FilterComponent extends D2Component<FilterFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<FilterModule, FilterComponent> {
    }
}
