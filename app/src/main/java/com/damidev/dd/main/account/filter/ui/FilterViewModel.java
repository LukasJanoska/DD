package com.damidev.dd.main.account.filter.ui;


import android.content.Context;
import android.view.View;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class FilterViewModel extends BaseViewModel<FilterView> {

    private Context context;

    @Inject
    public FilterViewModel(Context context) {
        this.context = context;
    }

    public void onClickSave(View view) {
        getView().save();
    }

}
