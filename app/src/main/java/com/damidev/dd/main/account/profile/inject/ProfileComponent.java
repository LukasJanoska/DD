package com.damidev.dd.main.account.profile.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.profile.ui.ProfileFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = ProfileModule.class
)
public interface ProfileComponent extends D2Component<ProfileFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<ProfileModule, ProfileComponent> {
    }
}
