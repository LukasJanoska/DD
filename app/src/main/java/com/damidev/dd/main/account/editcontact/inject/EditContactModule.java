package com.damidev.dd.main.account.editcontact.inject;

import android.content.Context;

import com.damidev.core.inject.D2Module;
import com.damidev.core.inject.scope.FragmentScope;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseContactsHandler;

import dagger.Module;
import dagger.Provides;

@Module
public class EditContactModule implements D2Module {

    @Provides
    @FragmentScope
    public DatabaseContactsHandler provideProfileHandler(Context context) {
        return new DatabaseContactsHandler(context);
    }

    @Provides
    @FragmentScope
    public ContactsCommunicator provideProfileComunicator() {
        return new ContactsCommunicator();
    }
}
