package com.damidev.dd.main.account.profileedit.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentProfileEditBinding;
import com.damidev.dd.main.account.profile.ui.ProfileFragment;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditComponent;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.Events.ErrorEvent;
import com.damidev.dd.shared.Events.ServerEvent;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.rest.platform.BusProvider;
import com.squareup.otto.Subscribe;

import butterknife.BindView;
import butterknife.OnClick;


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
    @BindView(R.id.opengalery)
    ImageButton openGalery;

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
            setEditTexts(userProfileId);
        }
    }

    private void setEditTexts(int userProfileId) {
        profile = getViewModel().getUserProfile(userProfileId);
        if(profile.get_name() == null) {
            profile.set_name("");
            getViewModel().getName().set(profile.get_name());
        } else {
            getViewModel().getName().set(profile.get_name());
        }
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
        serverRegResultDto = serverEvent.getServerRegResultDto();
        getViewModel().updateDbUserProfile(serverRegResultDto);
    }

    @Subscribe
    public void onErrorEvent(ErrorEvent errorEvent){
        Toast.makeText(getContext(),"Please, connect to the internet",Toast.LENGTH_SHORT).show();
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

    @Override
    public void replaceWithProfileFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        ProfileFragment accountFragment = ProfileFragment.newInstance(ProfileFragment.AccountFragmnetTag, userProfileId);
        ft.replace(R.id.fragment_main_container, accountFragment);
        ft.commit();
    }

    @OnClick(R.id.opengalery)
    public void onGaleryClicked(final View view) {
        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_VIEW);
        intent.setType("image/*");
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

}
