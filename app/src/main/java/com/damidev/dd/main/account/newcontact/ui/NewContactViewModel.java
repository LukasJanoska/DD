package com.damidev.dd.main.account.newcontact.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;
import com.damidev.dd.shared.dataaccess.Contact;
import com.damidev.dd.shared.dataaccess.ServerNewContactResultDto;
import com.damidev.dd.shared.utils.Utils;

import java.util.HashMap;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class NewContactViewModel extends BaseViewModel<NewContactView> {

    private Context context;
    private ContactsCommunicator communicator;
    private DatabaseHandler handler;
    private String token;

    private final ObservableField<CharSequence> name = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> surName = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> phone = new ObservableField<CharSequence>();

    private final ObservableField<CharSequence> nameError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> emailError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> phoneError = new ObservableField<CharSequence>();


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

    public ObservableField<CharSequence> getNameError() {
        return nameError;
    }
    public ObservableField<CharSequence> getEmailError() {
        return emailError;
    }

    public ObservableField<CharSequence> getPhoneError() {
        return phoneError;
    }

    @Inject
    public NewContactViewModel(Context context, ContactsCommunicator comunicator, DatabaseHandler handler) {
        this.context = context;
        this.communicator = comunicator;
        this.handler = handler;
    }

    public boolean validateInputs() {
        boolean valid = true;
        nameError.set(null);
        emailError.set(null);
        phoneError.set(null);

        if(TextUtils.isEmpty(name.get())) {
            nameError.set("name required");
            valid = false;
        } else {
            nameError.set(null);
        }
        if(TextUtils.isEmpty(email.get())) {
            emailError.set("email required");
            valid = false;
        } else if(!(Utils.isEmailValid(email.get().toString()))) {
            emailError.set("email not valid");
            valid = false;
        } else {
            emailError.set(null);
        }
        if(TextUtils.isEmpty(phone.get())) {
            phoneError.set("phone required");
            valid = false;
        } else {
            phoneError.set(null);
        }

        return valid;
    }

    public void onSaveClick() {

        if(!validateInputs()) {
            return;
        }

        HashMap hashMap = new HashMap();
        hashMap.put("name", getName().get().toString());
        hashMap.put("lastname", getSurName().get().toString());
        hashMap.put("phone", getPhone().get().toString());
        hashMap.put("email", getEmail().get().toString());

        //token get from prefs
        token = loadToken();
        communicator.addContact(token, hashMap);
    }

    public void addContactToDB(ServerNewContactResultDto resultDto) {

        Contact contact = new Contact();
        contact.setId(resultDto.getContact().getId());
        contact.setName(resultDto.getContact().getName());
        contact.setLastname(resultDto.getContact().getLastname());
        contact.setEmail(resultDto.getContact().getEmail());
        contact.setPhone(resultDto.getContact().getPhone());
        contact.setFid(resultDto.getContact().getFid());
        contact.setDescription(resultDto.getContact().getDescription());
        handler.addContact(contact);
        getView().replaceWithContactsFragment(loadToken());
    }

    public String loadToken() {
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getString("token", "");
    }

}
