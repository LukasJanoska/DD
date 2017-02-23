package com.damidev.dd.notregistred.map.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class MapViewModel extends BaseViewModel<MapFragView> {

    private Context context;

    @Inject
    public MapViewModel(Context context) {
        this.context = context;
    }
}
