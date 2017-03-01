package com.damidev.dd.notregistred.picture.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.notregistred.picture.ui.PictureFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = PictureModule.class
)
public interface PictureComponent extends D2Component<PictureFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<PictureModule, PictureComponent> {
    }
}
