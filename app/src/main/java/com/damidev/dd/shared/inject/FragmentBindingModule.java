package com.damidev.dd.shared.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.InjectKey;
import com.damidev.dd.notregistred.login.inject.LoginComponent;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.notregistred.map.inject.MapComponent;
import com.damidev.dd.notregistred.map.ui.MapFragment;
import com.damidev.dd.notregistred.register.inject.RegComponent;
import com.damidev.dd.notregistred.register.ui.RegFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module(
        subcomponents = {
                LoginComponent.class,
                MapComponent.class,
                RegComponent.class
        }
)
public abstract class FragmentBindingModule {

    @Binds
    @IntoMap
    @InjectKey(LoginFragment.class)
    public abstract ComponentBuilder loginFragmentComponentBuilder(LoginComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(MapFragment.class)
    public abstract ComponentBuilder mapFragmentComponentBuilder(MapComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(RegFragment.class)
    public abstract ComponentBuilder regFragmentComponentBuilder(RegComponent.Builder builder);

}