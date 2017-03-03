package com.damidev.dd.main.account.profileedit.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentProfileEditBinding;
import com.damidev.dd.main.account.profileedit.Events.ServerEvent;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditComponent;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;

import butterknife.BindView;


public class ProfileEditFragment extends D2MvvmFragment<FragmentProfileEditBinding, ProfileEditViewModel>
        implements ProfileEditView {

    public static String ProfileEditFragmnetTag = "PROFILE_EDIT_FRAGMENT_TAG";
    private int userProfileId;

    @BindView(R.id.profileNameTv)
    EditText profileNameTv;
    @BindView(R.id.profileSurNameTv)
    EditText profileSurNameTv;
    @BindView(R.id.profileEmail)
    EditText profleEmail;
    @BindView(R.id.profilePhone)
    EditText proflePhone;
    @BindView(R.id.profileDescription)
    EditText profleDescription;

    private Profile profile;
    private ServerRegResultDto serverRegResultDto;


    public ProfileEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userProfileId = bundle.getInt(LoginFragment.user_profile_id_tag, 0);
            profile = getViewModel().getUserProfile(userProfileId);
            getViewModel().getName().set(profile.get_name());
            if(profile.get_last_name() == null) {
                profile.set_last_name("");
                getViewModel().getSurName().set(profile.get_last_name());
            } else {
                getViewModel().getSurName().set(profile.get_last_name());
            }
            getViewModel().getEmail().set(profile.get_email());
            if(profile.get_phone() == null) {
                profile.set_phone("");
                getViewModel().getPhone().set(profile.get_phone());
            } else {
                getViewModel().getPhone().set(profile.get_phone());
            }
            if(profile.get_description() == null) {
                profile.set_description("");
                getViewModel().getDescr().set(profile.get_description());
            } else {
                getViewModel().getDescr().set(profile.get_description());
            }
        }
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((ProfileEditComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new ProfileEditModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_profile_edit);

        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    public static ProfileEditFragment newInstance(String someTitle, int userProfileId) {
        ProfileEditFragment loginFragment = new ProfileEditFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(LoginFragment.user_profile_id_tag, userProfileId);
        loginFragment.setArguments(bundle);
        return loginFragment;
    }

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){

        serverRegResultDto = serverEvent.getServerResponse();
        getViewModel().updateDbUserProfile(serverRegResultDto);
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }

}
