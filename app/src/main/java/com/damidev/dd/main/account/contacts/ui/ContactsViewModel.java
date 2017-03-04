package com.damidev.dd.main.account.contacts.ui;


import android.content.Context;
import android.content.SharedPreferences;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseContactsHandler;
import com.damidev.dd.shared.dataaccess.Contact;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto;
import com.damidev.dd.shared.dataaccess.ServerContactsResultDto.ContactsResponse;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;


public class ContactsViewModel extends BaseViewModel<ContactsView> {

    private Context context;
    private ContactsCommunicator communicator;
    private DatabaseContactsHandler handler;
    private String token;

    @Inject
    public ContactsViewModel(Context context, ContactsCommunicator communicator, DatabaseContactsHandler handler) {
        this.context = context;
        this.communicator = communicator;
        this.handler = handler;
    }

    public void getAllContacts(String token) {
        handler.deleteAllContacts();
        communicator.getAllContacts(token);
    }

    public List<Contact> saveContactsToDB(ServerContactsResultDto resultDto) {
        ArrayList<ContactsResponse> result = resultDto.getContacts();

        for (ContactsResponse cRes : result) {
            Contact contact = new Contact();
            contact.setId(cRes.getId());
            contact.setName(cRes.getName());
            contact.setLastname(cRes.getLastname());
            contact.setEmail(cRes.getEmail());
            contact.setPhone(cRes.getPhone());
            contact.setFid(cRes.getFid());
            contact.setDescription(cRes.getDescription());
            handler.addContact(contact);
        }

        return handler.getAllContacts();
    }

    public void onNewContactClick() {
        getView().replaceWithNewContactFragmnet();

    }

    public void deleteContact(int id) {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.addAll(handler.getAllContacts());

        token = loadToken();
        communicator.deleteContact(token, contacts.get(id).getId());
    }

    public String loadToken() {
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getString("token", "");
    }

}
