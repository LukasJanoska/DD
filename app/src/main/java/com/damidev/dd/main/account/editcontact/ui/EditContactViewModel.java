package com.damidev.dd.main.account.editcontact.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;
import com.damidev.dd.shared.dataaccess.Contact;

import java.util.ArrayList;
import java.util.HashMap;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class EditContactViewModel extends BaseViewModel<EditContactView> {

    private Context context;
    private ContactsCommunicator communicator;
    private DatabaseHandler handler;
    private String token;

    private final ObservableField<CharSequence> name = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> surName = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> phone = new ObservableField<CharSequence>();

    public ObservableField<CharSequence> getName() {
        return name;
    }
    public ObservableField<CharSequence> getSurName() {
        return surName;
    }
    public ObservableField<CharSequence> getEmail() {
        return email;
    }
    public ObservableField<CharSequence> getPhone() {
        return phone;
    }

    @Inject
    public EditContactViewModel(Context context, ContactsCommunicator comunicator, DatabaseHandler handler) {
        this.context = context;
        this.communicator = comunicator;
        this.handler = handler;
    }

    public Contact getContact(int id) {
        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.addAll(handler.getAllContacts());
        Contact contact = contacts.get(id);

        if(contact!=null) {
            return contact;
        }
        return null;
    }

    public void onSaveClick(int contact_id) {

        ArrayList<Contact> contacts = new ArrayList<>();
        contacts.addAll(handler.getAllContacts());
        Contact contact = contacts.get(contact_id);

        HashMap hashMap = new HashMap();
        hashMap.put("id", Integer.toString(contact.getId()));
        hashMap.put("name", getName().get().toString());
        hashMap.put("lastname", getSurName().get().toString());
        hashMap.put("phone", getPhone().get().toString());
        hashMap.put("email", getEmail().get().toString());

        //token get from prefs
        token = loadToken();
        communicator.updateContact(token, hashMap);
    }

    public void replaceWithContactFragment() {
        getView().replaceWithContactsFragment(loadToken());
    }

    public String loadToken() {
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getString("token", "");
    }

}
