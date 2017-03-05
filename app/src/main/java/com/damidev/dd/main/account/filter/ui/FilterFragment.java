package com.damidev.dd.main.account.filter.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentProfileBinding;
import com.damidev.dd.main.account.filter.inject.FilterComponent;
import com.damidev.dd.main.account.filter.inject.FilterModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;


public class FilterFragment extends D2MvvmFragment<FragmentProfileBinding, FilterViewModel>
        implements FilterView {

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((FilterComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new FilterModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_filter);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static FilterFragment newInstance(String someTitle) {
        FilterFragment loginFragment = new FilterFragment();
        return loginFragment;
    }

  /*  @MainThread
    @Override
    public void replaceWithProfileEditFragmnet() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ProfileEditFragment fragment = ProfileEditFragment.newInstance(ProfileEditFragment.ProfileEditFragmnetTag, userProfileId);
        ft.replace(R.id.fragment_main_container, fragment); //.addToBackStack(null);
        ft.commit();
    }*/

}
