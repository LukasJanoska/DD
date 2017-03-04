package com.damidev.dd.main.account.newcontact.inject;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.newcontact.platform.NewContactCommunicator;

import dagger.Module;
import dagger.Provides;

@Module
public class NewContactModule implements D2Module {

    /*@Provides
    @FragmentScope
    public DatabaseContactsHandler provideProfileHandler(Context context) {
        return new DatabaseContactsHandler(context);
    }*/

    @Provides
    @FragmentScope
    public NewContactCommunicator provideProfileComunicator() {
        return new NewContactCommunicator();
    }
}
