package com.damidev.core.retain;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;


public class RetainFragment<T>  extends Fragment {
    @NonNull
    public static <T> RetainFragment<T> newInstance() {
        final RetainFragment<T> retainFragment = new RetainFragment<>();
        retainFragment.setRetainInstance(true);
        return retainFragment;
    }

    private T object;

    @NonNull
    public T getObject() {
        return object;
    }

    public void setObject(@NonNull T object) {
        this.object = object;
    }
}
