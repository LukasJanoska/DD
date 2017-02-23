/*
package com.damidev.dd.main.ui;

import android.os.Bundle;
import android.support.annotation.MainThread;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivityRegistrationBinding;
import com.damidev.dd.registration.inject.RegistrationComponent;
import com.damidev.dd.registration.inject.RegistrationModule;
import com.damidev.dd.shared.dialog.progressdialog.ui.ErrorDialog;
import com.damidev.dd.shared.dialog.progressdialog.ui.ProgressDialog;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;


public class MainActivity extends D2MvvmActivity<ActivityRegistrationBinding, MainViewModel>
        implements MainView {

    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_registration, savedInstanceState);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((RegistrationComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .activityModule(new ActivityModule(this))
                .module(new RegistrationModule())
                .build()
                .injectMembers(this);
    }

    @MainThread
    @Override
    public void showProgressDialog() {
        progressDialog = ProgressDialog.newInstance("loading");
        progressDialog.show(getSupportFragmentManager(), "progress");
    }

    @MainThread
    @Override
    public void hideProgressDialog() {
        if(progressDialog != null) {
            progressDialog.dismiss();
        }
    }

    @MainThread
    @Override
    public void showErrorDialog(final String errMsg) {
        ErrorDialog.newInstance(errMsg).show(getSupportFragmentManager(), "error");
    }

    @MainThread
    @Override
    public void showErrorToast(final String errMsg) {
        Toast.makeText(getApplicationContext(), errMsg, Toast.LENGTH_LONG).show();
    }
}
*/
