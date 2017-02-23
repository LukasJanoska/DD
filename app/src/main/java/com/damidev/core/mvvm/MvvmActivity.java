package com.damidev.core.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.damidev.dd.BR;

import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * @author Lukas Janoska
 */

public class MvvmActivity<DB extends ViewDataBinding, MV extends MvvmViewModel> extends AppCompatActivity {

    /** Local variables */
    private final String tag;
    private MV viewModel;
    private DB binding;

    public MvvmActivity() {
        this.tag = getClass().getSimpleName() + ": ";
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    protected final void setAndBindContentView(@LayoutRes int layoutResId,
                                               @Nullable Bundle savedInstanceState) {
        if(viewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected via activityComponent().inject(this)");
        }

        binding = DataBindingUtil.setContentView(this, layoutResId);
        binding.setVariable(BR.vm, getViewModel());

        Timber.d(log("binding complete"));
    }

    @Override
    public void onStart() {
        super.onStart();
        Timber.d(log("onStart"));
        getViewModel().attachView((MvvmView) this);
    }

    @Override
    public void onResume() {
        super.onResume();
        Timber.d(log("onResume"));
        getViewModel().viewResumed();
    }

    @Override
    public void onStop() {
        super.onStop();
        Timber.d(log("onStop"));

        if(getViewModel() != null) {
            getViewModel().detachView();
        }
    }

    @Override
    @CallSuper
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Timber.d(log("onSaveInstanceState"));

        if(viewModel != null) {
            viewModel.saveInstanceState(outState);
        }
    }

    @Override
    @CallSuper
    protected void onDestroy() {
        super.onDestroy();
        Timber.d(log("onDestroy"));

        binding = null;
        viewModel = null;
    }

    protected String log(final String message) {
        return tag + message;
    }

    public MV getViewModel() {
        return viewModel;
    }

    public void setViewModel(MV viewModel) {
        this.viewModel = viewModel;
    }

    public DB getBinding() {
        return binding;
    }
}