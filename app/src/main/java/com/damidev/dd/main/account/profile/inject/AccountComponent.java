package com.damidev.dd.main.account.profile.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.profile.ui.AccountFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = AccountModule.class
)
public interface AccountComponent extends D2Component<AccountFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<AccountModule, AccountComponent> {
    }
}
