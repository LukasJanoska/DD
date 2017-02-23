package com.damidev.dd.notregistred.login.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.inject.FragmentModule;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = { LoginModule.class, FragmentModule.class }
)
public interface LoginComponent extends D2Component<LoginFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<LoginModule, LoginComponent> {
        LoginComponent.Builder fragmentModule(FragmentModule fragmentModule);
    }
}
