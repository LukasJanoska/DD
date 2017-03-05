package com.damidev.dd.main.account.map.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class LoggedMapViewModel extends BaseViewModel<LoggedMapFragView> {

    private Context context;

    @Inject
    public LoggedMapViewModel(Context context) {
        this.context = context;
    }
}
