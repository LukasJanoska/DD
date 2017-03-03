package com.damidev.dd.shared.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.InjectKey;
import com.damidev.dd.main.account.profile.inject.AccountComponent;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditComponent;
import com.damidev.dd.main.account.profileedit.ui.ProfileEditFragment;
import com.damidev.dd.main.account.profile.ui.AccountFragment;
import com.damidev.dd.notregistred.login.inject.LoginComponent;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.notregistred.map.inject.MapComponent;
import com.damidev.dd.notregistred.map.ui.MapFragment;
import com.damidev.dd.notregistred.picture.inject.PictureComponent;
import com.damidev.dd.notregistred.picture.ui.PictureFragment;
import com.damidev.dd.notregistred.register.inject.RegComponent;
import com.damidev.dd.notregistred.register.ui.RegFragment;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;


@Module(
        subcomponents = {
                LoginComponent.class,
                MapComponent.class,
                RegComponent.class,
                PictureComponent.class,
                AccountComponent.class,
                ProfileEditComponent.class
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

    @Binds
    @IntoMap
    @InjectKey(PictureFragment.class)
    public abstract ComponentBuilder pictureFragmentComponentBuilder(PictureComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(AccountFragment.class)
    public abstract ComponentBuilder accountFragmentComponentBuilder(AccountComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(ProfileEditFragment.class)
    public abstract ComponentBuilder profileEditFragmentComponentBuilder(ProfileEditComponent.Builder builder);

}