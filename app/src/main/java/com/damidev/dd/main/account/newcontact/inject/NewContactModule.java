package com.damidev.dd.main.account.newcontact.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class NewContactModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseHandler provideProfileHandler(Context context) {
        return new DatabaseHandler(context);
    }

    @Provides
    @FragmentScope
    public ContactsCommunicator provideProfileComunicator() {
        return new ContactsCommunicator();
    }
}
