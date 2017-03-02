package com.damidev.dd.main.account.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.notregistred.login.platform.DatabaseProfileHandler;

import javax.inject.Inject;


public class AccountViewModel extends BaseViewModel<AccountView> {

    private Context context;
    private DatabaseProfileHandler profiledb;

    @Inject
    public AccountViewModel(Context context, DatabaseProfileHandler profiledb) {
        this.context = context;
        this.profiledb = profiledb;
    }


}
