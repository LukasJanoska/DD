package com.damidev.dd.main.account.profile.ui;


import com.damidev.core.mvvm.MvvmView;
import com.damidev.dd.notregistred.login.dataaccess.Profile;

public interface AccountView extends MvvmView {
    void setAttrsToView(Profile pr);
    void replaceWithProfileEditFragmnet();
}
