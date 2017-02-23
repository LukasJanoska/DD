package com.damidev.dd.shared.inject;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.damidev.core.inject.scope.FragmentScope;

import java.lang.ref.WeakReference;

import dagger.Module;
import dagger.Provides;

/**
 * @author Lukas Janoska
 */
@Module
public class FragmentModule {

    private final WeakReference<Fragment> fragment;

    public FragmentModule(Fragment fragment) {
        this.fragment = new WeakReference<Fragment>(fragment);
    }

    @Provides
    @FragmentScope
    FragmentManager provideFragmentManager() {
        return fragment.get().getActivity().getSupportFragmentManager();
    }
}