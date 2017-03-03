package com.damidev.dd.main.account.contacts.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class ContactsViewModel extends BaseViewModel<ContactsView> {

    private Context context;

    @Inject
    public ContactsViewModel(Context context) {
        this.context = context;
    }

}
