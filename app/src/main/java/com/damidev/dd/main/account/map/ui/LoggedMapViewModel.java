package com.damidev.dd.main.account.map.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.contacts.platform.DatabaseHandler;
import com.damidev.dd.shared.dataaccess.Profile;

import javax.inject.Inject;


public class LoggedMapViewModel extends BaseViewModel<LoggedMapFragView> {

    private Context context;
    private DatabaseHandler profiledb;

    @Inject
    public LoggedMapViewModel(Context context, DatabaseHandler profiledb) {
        this.context = context;
        this.profiledb = profiledb;
    }

    public Profile getUserProfile(int userProfileId) {
        Profile pr = profiledb.getProfile(userProfileId);

        if(pr!=null) {
            return pr;
        }
        return null;
    }

}
