package com.damidev.dd.main.account.profile.ui;


import com.damidev.core.mvvm.MvvmView;
import com.damidev.dd.shared.dataaccess.Profile;

public interface ProfileView extends MvvmView {
    void setAttrsToView(Profile pr);
    void replaceWithProfileEditFragmnet();
}
