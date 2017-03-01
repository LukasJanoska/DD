package com.damidev.dd.main.account.ui;


import android.content.Context;
import android.databinding.ObservableField;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class AccountViewModel extends BaseViewModel<AccountView> {

    private Context context;

    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> password = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> usernameError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordError = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> passwordErrorr = new ObservableField<CharSequence>();

    @Inject
    public AccountViewModel(Context context) {
        this.context = context;
    }


    public ObservableField<CharSequence> getEmail() {
        return email;
    }

    public ObservableField<CharSequence> getPassword() {
        return password;
    }

    public ObservableField<CharSequence> getUsernameError() {
        return usernameError;
    }

    public ObservableField<CharSequence> getPasswordError() {
        return passwordError;
    }

    public ObservableField<CharSequence> getPasswordErrorr() {
        return passwordErrorr;
    }
}
