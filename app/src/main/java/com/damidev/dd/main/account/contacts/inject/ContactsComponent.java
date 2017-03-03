package com.damidev.dd.main.account.contacts.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = ContactsModule.class
)
public interface ContactsComponent extends D2Component<ContactsFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<ContactsModule, ContactsComponent> {
    }
}
