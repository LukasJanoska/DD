package com.damidev.dd.notregistred.picture.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;

import javax.inject.Inject;


public class PictureViewModel extends BaseViewModel<PictureView> {

    private Context context;

    @Inject
    public PictureViewModel(Context context) {
        this.context = context;
    }

}
