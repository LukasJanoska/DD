package com.damidev.dd.main.account.filter.ui;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentProfileBinding;
import com.damidev.dd.main.account.filter.inject.FilterComponent;
import com.damidev.dd.main.account.filter.inject.FilterModule;
import com.damidev.dd.main.account.map.ui.LoggedMapFragment;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.inject.D2MvvmFragment;

import butterknife.BindView;

import static android.content.Context.MODE_PRIVATE;


public class FilterFragment extends D2MvvmFragment<FragmentProfileBinding, FilterViewModel>
        implements FilterView {

    @BindView(R.id.filterCheckBox)
    CheckBox box;
    private int userProfileId;

    public FilterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userProfileId = bundle.getInt(LoginFragment.user_profile_id_tag, 0);
        }
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
        box.setChecked(loadFilterChecked());
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static FilterFragment newInstance(String someTitle, int userProfileId) {
        FilterFragment fragment = new FilterFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LoginFragment.user_profile_id_tag, userProfileId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void save() {
        saveBoxCheckedToPrefs("filterbox", box.isChecked());
        replaceWithLoggedMapFragmnet();
    }

    public boolean loadFilterChecked() {
        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        return prefs.getBoolean("filterbox", false);
    }

    public void saveBoxCheckedToPrefs(String key, boolean value) {
        SharedPreferences prefs = getContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        prefs.edit()
                .putBoolean(key, value)
                .apply();
    }

    @MainThread
    public void replaceWithLoggedMapFragmnet() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        LoggedMapFragment fragment = LoggedMapFragment.newInstance(LoggedMapFragment.LoggedMapFragmnetTag, userProfileId);
        ft.replace(R.id.fragment_main_container, fragment); //.addToBackStack(null);
        ft.commit();
    }

}
