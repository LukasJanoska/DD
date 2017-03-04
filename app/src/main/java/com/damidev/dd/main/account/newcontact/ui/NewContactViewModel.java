package com.damidev.dd.main.account.newcontact.ui;


import android.content.Context;
import android.content.SharedPreferences;
import android.databinding.ObservableField;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.newcontact.platform.NewContactCommunicator;

import java.util.HashMap;

import javax.inject.Inject;

import static android.content.Context.MODE_PRIVATE;


public class NewContactViewModel extends BaseViewModel<NewContactView> {

    private Context context;
    private NewContactCommunicator communicator;
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
    public NewContactViewModel(Context context, NewContactCommunicator comunicator) {
        this.context = context;
        this.communicator = comunicator;
    }

    public void onSaveClick() {
        HashMap hashMap = new HashMap();

        hashMap.put("name", getName().get().toString());
        hashMap.put("lastname", getSurName().get().toString());
        hashMap.put("phone", getPhone().get().toString());
        hashMap.put("email", getEmail().get().toString());
        //token get from prefs
        token = loadToken();
        communicator.addContact(token, hashMap);

    }

    public String loadToken() {
        SharedPreferences prefs = context.getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getString("token", "");
    }

}
