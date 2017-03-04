package com.damidev.dd.main.account.newcontact.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.newcontact.ui.NewContactFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = NewContactModule.class
)
public interface NewContactComponent extends D2Component<NewContactFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<NewContactModule, NewContactComponent> {
    }
}
