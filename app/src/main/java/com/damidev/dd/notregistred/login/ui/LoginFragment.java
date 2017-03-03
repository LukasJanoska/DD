package com.damidev.dd.notregistred.login.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentLoginBinding;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.notregistred.login.inject.LoginComponent;
import com.damidev.dd.notregistred.login.inject.LoginModule;
import com.damidev.dd.notregistred.register.ui.RegFragment;
import com.damidev.dd.shared.dialog.progressdialog.ui.ErrorDialog;
import com.damidev.dd.shared.dialog.progressdialog.ui.ProgressDialog;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.inject.FragmentModule;


public class LoginFragment extends D2MvvmFragment<FragmentLoginBinding, LoginViewModel>
        implements LoginView {

    private ProgressDialog progressDialog;

    public static String user_profile_id_tag = "user_profile_id";
    public static String LoginFragmnetTag = "LOGIN_FRAGMENT_TAG";

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getViewModel().getEmail().set("mmmm@mm.mm");
        getViewModel().getPassword().set("mmm");
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((LoginComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .fragmentModule(new FragmentModule(this))
                .module(new LoginModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_login);

        return view;
    }

    public static LoginFragment newInstance(String someTitle) {
        LoginFragment loginFragment = new LoginFragment();
        return loginFragment;
    }

    @MainThread
    @Override
    public void showProgressDialog() {
        progressDialog = ProgressDialog.newInstance("loading");
        progressDialog.show(getActivity().getSupportFragmentManager(), "progress");
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
        ErrorDialog.newInstance(errMsg).show(getActivity().getSupportFragmentManager(), "error");
    }

    @MainThread
    @Override
    public void showErrorToast(final String errMsg) {
        Toast.makeText(getContext(), errMsg, Toast.LENGTH_LONG).show();
    }

    @MainThread
    @Override
    public void replaceWithRegFragment() {
        FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
        RegFragment loginFragment = RegFragment.newInstance(RegFragment.RegFragmnetTag);
        ft.replace(R.id.fragment_container, loginFragment);
        ft.commit();
    }

    @Override
    public void startMainActivity(int userProfileId) {
        getActivity().finish();
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        myIntent.putExtra(user_profile_id_tag, userProfileId);
        startActivity(myIntent);
    }
}
