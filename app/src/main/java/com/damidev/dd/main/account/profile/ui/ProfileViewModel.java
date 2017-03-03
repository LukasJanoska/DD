package com.damidev.dd.main.account.profile.ui;


import android.content.Context;
import android.databinding.ObservableField;

import com.damidev.core.mvvm.BaseViewModel;
import com.damidev.dd.shared.dataaccess.Profile;
import com.damidev.dd.main.account.profileedit.platform.DatabaseProfileHandler;

import javax.inject.Inject;


public class ProfileViewModel extends BaseViewModel<ProfileView> {

    private Context context;
    private DatabaseProfileHandler profiledb;

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
    public ProfileViewModel(Context context, DatabaseProfileHandler profiledb) {
        this.context = context;
        this.profiledb = profiledb;
    }

    public Profile getUserProfile(int userProfileId) {
        Profile pr = profiledb.getProfile(userProfileId);

        if(pr!=null) {
            return pr;
        }
        return null;
    }

    public void onEditProfile() {
        getView().replaceWithProfileEditFragmnet();
    }

}
