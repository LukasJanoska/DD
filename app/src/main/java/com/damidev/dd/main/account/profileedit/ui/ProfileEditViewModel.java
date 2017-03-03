package com.damidev.dd.main.account.profileedit.ui;


import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.main.account.profileedit.platform.ProfileCommunicator;
import com.damidev.dd.notregistred.login.platform.DatabaseProfileHandler;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.shared.dataaccess.ServerRegResultDto;

import java.util.HashMap;

import javax.inject.Inject;


public class ProfileEditViewModel extends BaseViewModel<ProfileEditView> {

    private Context context;
    private DatabaseProfileHandler profiledb;
    private ProfileCommunicator communicator;

    private final ObservableField<CharSequence> name = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> surName = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> email = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> phone = new ObservableField<CharSequence>();
    private final ObservableField<CharSequence> descr = new ObservableField<CharSequence>();

    public ObservableField<CharSequence> getName() {
        return name;
    }

    public ObservableField<CharSequence> getSurName() {
        return surName;
    }

    public ObservableField<CharSequence> getEmail() {
        return email;
    }

    public ObservableField<CharSequence> getPhone() {
        return phone;
    }

    public ObservableField<CharSequence> getDescr() {
        return descr;
    }

    @Inject
    public ProfileEditViewModel(Context context, DatabaseProfileHandler profiledb, ProfileCommunicator communicator) {
        this.context = context;
        this.profiledb = profiledb;
        this.communicator = communicator;
    }

    public Profile getUserProfile(int userProfileId) {
        Profile pr = profiledb.getProfile(userProfileId);

        if(pr!=null) {
            return pr;
        }
        return null;
    }

    public void onSaveChanges(View view) {
        HashMap hashMap = new HashMap();

        hashMap.put("name", getName().get().toString());
        hashMap.put("lastname", getSurName().get().toString());
        hashMap.put("email", getEmail().get().toString());
        hashMap.put("phone", getPhone().get().toString());
        hashMap.put("description", getDescr().get().toString());

        communicator.updateUserProfile(hashMap);
    }

    public void updateDbUserProfile(ServerRegResultDto response) {
        Profile profile = new Profile();

        profile.set_id(response.getChildResponse().getId());
        profile.set_email(response.getChildResponse().getEmail());
        profile.set_name(response.getChildResponse().getName());
        profile.set_last_name(response.getChildResponse().getLastname());
        profile.set_phone(response.getChildResponse().getPhone());
        profile.set_description(response.getChildResponse().getDescription());

        profiledb.updateProfile(profile);

        getView().replaceWithProfileFragment();

    }

}
