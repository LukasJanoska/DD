package com.damidev.dd.splashscreen.ui;


import android.content.Context;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.core.mvvm.NoMvvmView;

import javax.inject.Inject;


public class SplashScreenViewModel extends BaseViewModel<NoMvvmView> {
    Context context;

    @Inject
    public SplashScreenViewModel(Context context) {
        this.context = context;

    }
}