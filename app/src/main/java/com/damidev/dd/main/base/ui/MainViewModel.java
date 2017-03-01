package com.damidev.dd.main.base.ui;

import android.content.Context;

import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.core.mvvm.rx.RxRetainBaseViewModel;

import javax.inject.Inject;


@ActivityScope
public class MainViewModel extends RxRetainBaseViewModel<MainView> {

    Context context;

    @Inject
    public MainViewModel(Context context) {
        this.context = context;

    }


}
