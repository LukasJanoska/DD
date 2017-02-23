package com.damidev.dd.notregistred.base.ui;

import android.content.Context;

import com.damidev.core.inject.scope.ActivityScope;
import com.damidev.core.mvvm.rx.RxRetainBaseViewModel;

import javax.inject.Inject;




@ActivityScope
public class NotRegistredViewModel extends RxRetainBaseViewModel<NotRegistredView> {

    Context context;

    @Inject
    public NotRegistredViewModel(Context context) {
        this.context = context;

    }


}
