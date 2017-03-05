package com.damidev.dd.main.account.contacts.ui;


import com.damidev.core.mvvm.MvvmView;
import com.damidev.dd.shared.dataaccess.Contact;

import java.util.ArrayList;

public interface ContactsView extends MvvmView {
    void replaceWithNewContactFragmnet();
    void setContactsAdapterAfterSearch(ArrayList<Contact> contacts);
}
