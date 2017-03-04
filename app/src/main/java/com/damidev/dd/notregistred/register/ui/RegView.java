package com.damidev.dd.notregistred.register.ui;


import com.damidev.core.mvvm.MvvmView;

public interface RegView extends MvvmView {
    void showProgressDialog();
    void hideProgressDialog();
    void showErrorDialog(final String errMsg);
    void showErrorToast(final String errMsg);
    void startMainActivity(int userProfileId, String token);
}
