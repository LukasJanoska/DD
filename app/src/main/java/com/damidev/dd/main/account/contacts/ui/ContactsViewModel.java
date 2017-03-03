package com.damidev.dd.main.account.contacts.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;

import javax.inject.Inject;


public class ContactsViewModel extends BaseViewModel<ContactsView> {

    private Context context;
    private ContactsCommunicator communicator;

    @Inject
    public ContactsViewModel(Context context, ContactsCommunicator communicator) {
        this.context = context;
        this.communicator = communicator;
    }

    public void getAllContacts(String token) {
        communicator.getAllContacts(token);
    }

}
