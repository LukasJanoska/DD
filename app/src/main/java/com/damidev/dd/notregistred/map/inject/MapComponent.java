package com.damidev.dd.notregistred.map.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.D2Component;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.notregistred.map.ui.MapFragment;

import dagger.Subcomponent;


@FragmentScope
@Subcomponent(
        modules = MapModule.class
)
public interface MapComponent extends D2Component<MapFragment> {

    @Subcomponent.Builder
    interface Builder extends ComponentBuilder<MapModule, MapComponent> {
    }
}
