package com.damidev.dd.main.account.profile.ui;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentAccountBinding;
import com.damidev.dd.main.account.profile.inject.AccountComponent;
import com.damidev.dd.main.account.profile.inject.AccountModule;
import com.damidev.dd.main.account.profileedit.ui.ProfileEditFragment;
import com.damidev.dd.notregistred.login.dataaccess.Profile;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.inject.D2MvvmFragment;

import butterknife.BindView;


public class AccountFragment extends D2MvvmFragment<FragmentAccountBinding, AccountViewModel>
        implements AccountView {

    public static String AccountFragmnetTag = "ACCOUNT_FRAGMENT_TAG";
    private int userProfileId;

    @BindView(R.id.profileNameTv)
    TextView profileNameTv;
    @BindView(R.id.profileSurNameTv)
    TextView profileSurNameTv;
    @BindView(R.id.profileEmail)
    TextView profleEmail;
    @BindView(R.id.profilePhone)
    TextView proflePhone;
    @BindView(R.id.profileDescription)
    TextView profleDescription;

    private Profile profile;

    public AccountFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userProfileId = bundle.getInt(LoginFragment.user_profile_id_tag, 0);
            profile = getViewModel().getUserProfile(userProfileId);
            getViewModel().getName().set(profile.get_name());
            getViewModel().getSurName().set(profile.get_last_name());
            getViewModel().getEmail().set(profile.get_email());
            getViewModel().getPhone().set(profile.get_phone());
            getViewModel().getDescr().set(profile.get_description());
        }
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((AccountComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new AccountModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_account);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.menu_profile_fragment, menu);
        super.onCreateOptionsMenu(menu,inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.edit_profile:
                getViewModel().onEditProfile();
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }

    public static AccountFragment newInstance(String someTitle, int userProfileId) {
        AccountFragment loginFragment = new AccountFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LoginFragment.user_profile_id_tag, userProfileId);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Override
    public void setAttrsToView(Profile pr) {
        profileNameTv.setText(pr.get_name());
        profileSurNameTv.setText(pr.get_last_name());
    }

    @MainThread
    @Override
    public void replaceWithProfileEditFragmnet() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ProfileEditFragment fragment = ProfileEditFragment.newInstance(ProfileEditFragment.ProfileEditFragmnetTag, userProfileId);
        ft.replace(R.id.fragment_main_container, fragment); //.addToBackStack(null);
        ft.commit();
    }

}
