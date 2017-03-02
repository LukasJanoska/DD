package com.damidev.dd.main.account.profileedit.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.profileedit.ui.ProfileEditFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = ProfileEditModule.class
)
public interface ProfileEditComponent extends D2Component<ProfileEditFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<ProfileEditModule, ProfileEditComponent> {
    }
}
