package com.damidev.core.mvvm;

import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.dd.BR;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import timber.log.Timber;

/**
 * @author Lukas Janoska
 */
public class MvvmFragment<DB extends ViewDataBinding, MV extends MvvmViewModel> extends Fragment {

    /** Local variables */
    private final String tag;
    private MV viewModel;
    private DB binding;
    protected Unbinder unbinder;

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

    public MvvmFragment() {
        this.tag = getClass().getSimpleName() + ": ";
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

    protected final View setAndBindContentView(@NonNull LayoutInflater inflater,
                                               @Nullable ViewGroup container,
                                               @LayoutRes int layoutResId) {
        if(viewModel == null) {
            throw new IllegalStateException("viewModel must not be null and should be injected " +
                    "via activityComponent().inject(this)");
        }

        binding = DataBindingUtil.inflate(inflater, layoutResId, container, false);
        binding.setVariable(BR.vm, getViewModel());

        unbinder = ButterKnife.bind(this, binding.getRoot());

        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}