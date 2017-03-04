package com.damidev.dd.main.account.editcontact.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.editcontact.ui.EditContactFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = EditContactModule.class
)
public interface EditContactComponent extends D2Component<EditContactFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<EditContactModule, EditContactComponent> {
    }
}
