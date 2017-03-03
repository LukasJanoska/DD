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
import com.damidev.dd.main.account.profileedit.platform.ProfileCommunicator;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditComponent;
import com.damidev.dd.main.account.profileedit.inject.ProfileEditModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.inject.D2MvvmFragment;

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

    private ProfileCommunicator communicator;

    private Profile profile;

    public ProfileEditFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = this.getArguments();
        if (bundle != null) {
            userProfileId = bundle.getInt(LoginFragment.user_profile_id_tag, 0);
            /*profile = getViewModel().getUserProfile(userProfileId);
            getViewModel().getName().set(profile.get_name());
            getViewModel().getSurName().set(profile.get_last_name());
            getViewModel().getEmail().set(profile.get_email());
            getViewModel().getPhone().set(profile.get_phone());
            getViewModel().getDescr().set(profile.get_description());*/
        }
        communicator = new ProfileCommunicator();
        usePost();
    }

    private void usePost(){
        communicator.updateUserProfile(getActivity().getApplicationContext());
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

}
