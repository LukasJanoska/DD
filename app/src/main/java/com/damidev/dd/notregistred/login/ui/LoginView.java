package com.damidev.dd.notregistred.login.ui;


import com.damidev.core.mvvm.MvvmView;

public interface LoginView extends MvvmView {

    void showProgressDialog();
    void hideProgressDialog();
    void showErrorDialog(final String errMsg);
    void showErrorToast(final String errMsg);
    void replaceWithRegFragment();
    void startMainActivity(int userProfileId);
}
