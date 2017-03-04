package com.damidev.dd.main.account.editcontact.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.ContactsCommunicator;
import com.damidev.dd.main.account.contacts.platform.DatabaseContactsHandler;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;

public class EditContactViewModel extends BaseViewModel<EditContactView> {

    private Context context;
    private ContactsCommunicator communicator;
    private DatabaseContactsHandler handler;

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
    public EditContactViewModel(Context context, ContactsCommunicator comunicator, DatabaseContactsHandler handler) {
        this.context = context;
        this.communicator = comunicator;
        this.handler = handler;
    }



    public String loadToken() {
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getString("token", "");
    }

}
