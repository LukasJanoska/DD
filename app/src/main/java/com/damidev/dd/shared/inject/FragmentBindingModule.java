package com.damidev.dd.shared.inject;

import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.InjectKey;
import com.damidev.dd.main.account.contacts.inject.ContactsComponent;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;
import com.damidev.dd.main.account.editcontact.inject.EditContactComponent;
import com.damidev.dd.main.account.editcontact.ui.EditContactFragment;
import com.damidev.dd.main.account.filter.inject.FilterComponent;
import com.damidev.dd.main.account.filter.ui.FilterFragment;
import com.damidev.dd.main.account.map.inject.LoggedMapComponent;
import com.damidev.dd.main.account.map.ui.LoggedMapFragment;
import com.damidev.dd.main.account.newcontact.inject.NewContactComponent;
import com.damidev.dd.main.account.newcontact.ui.NewContactFragment;
import com.damidev.dd.main.account.profile.inject.ProfileComponent;
import com.damidev.dd.main.account.profile.ui.ProfileFragment;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditComponent;
import com.damidev.dd.main.account.profileedit.ui.ProfileEditFragment;
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
                ProfileComponent.class,
                ProfileEditComponent.class,
                ContactsComponent.class,
                NewContactComponent.class,
                EditContactComponent.class,
                LoggedMapComponent.class,
                FilterComponent.class
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
    @InjectKey(ProfileFragment.class)
    public abstract ComponentBuilder accountFragmentComponentBuilder(ProfileComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(ProfileEditFragment.class)
    public abstract ComponentBuilder profileEditFragmentComponentBuilder(ProfileEditComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(ContactsFragment.class)
    public abstract ComponentBuilder contactsFragmentComponentBuilder(ContactsComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(NewContactFragment.class)
    public abstract ComponentBuilder newContactFragmentComponentBuilder(NewContactComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(EditContactFragment.class)
    public abstract ComponentBuilder editContactFragmentComponentBuilder(EditContactComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(LoggedMapFragment.class)
    public abstract ComponentBuilder loggedMapFragmentComponentBuilder(LoggedMapComponent.Builder builder);

    @Binds
    @IntoMap
    @InjectKey(FilterFragment.class)
    public abstract ComponentBuilder filterMapFragmentComponentBuilder(FilterComponent.Builder builder);

}