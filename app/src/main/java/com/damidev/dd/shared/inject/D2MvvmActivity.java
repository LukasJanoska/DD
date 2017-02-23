package com.damidev.dd.shared.inject;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.core.mvvm.MvvmActivity;
import com.damidev.core.mvvm.MvvmViewModel;
import com.damidev.dd.DDApplication;

import javax.inject.Inject;

/**
 * @author Lukas Janoska
 */
public abstract class D2MvvmActivity<DB extends ViewDataBinding, MV extends MvvmViewModel> extends MvvmActivity<DB, MV> {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setupComponent(DDApplication.get(this));
    }

    protected abstract void setupComponent(final ComponentBuilderContainer componentBuilder);

    @Inject
    public void setViewModel(MV viewModel) {
        super.setViewModel(viewModel);
    }
}
