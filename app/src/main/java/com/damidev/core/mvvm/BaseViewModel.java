package com.damidev.core.mvvm;

import android.databinding.BaseObservable;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;

import timber.log.Timber;

/**
 * @author Lukas Janoska
 */
public abstract class BaseViewModel<V extends MvvmView> extends BaseObservable implements MvvmViewModel<V> {

    private V view;

    public V getView() {
        return view;
    }

    @Override
    @CallSuper
    public void viewResumed() {
        Timber.d("View resumed");
    }

    @Override
    @CallSuper
    public void attachView(@NonNull V view) {
        this.view = view;

        Timber.d("View attached");
    }

    @Override
    @CallSuper
    public void detachView() {
        view = null;
        Timber.d("View detached");
    }

    @Override
    public void saveInstanceState(@NonNull Bundle outState) {
    }

    @Override
    public void restoreInstanceState(@NonNull Bundle savedInstanceState) {
    }
}