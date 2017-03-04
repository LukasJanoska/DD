package com.damidev.dd.notregistred.register.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.MainThread;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentRegBinding;
import com.damidev.dd.main.base.ui.MainActivity;
import com.damidev.dd.notregistred.register.inject.RegComponent;
import com.damidev.dd.notregistred.register.inject.RegModule;
import com.damidev.dd.shared.dialog.progressdialog.ui.ErrorDialog;
import com.damidev.dd.shared.dialog.progressdialog.ui.ProgressDialog;
import com.damidev.dd.shared.inject.D2MvvmFragment;
import com.damidev.dd.shared.inject.FragmentModule;


public class RegFragment extends D2MvvmFragment<FragmentRegBinding, RegViewModel>
        implements RegView {

    public static String RegFragmnetTag = "REG_FRAGMENT_TAG";

    private ProgressDialog progressDialog;

    public static String user_profile_id_tag = "user_profile_id";
    public static String user_token = "user_token";

    public RegFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        /*  getViewModel().getEmail().set("abc@ab.ab");
        getViewModel().getPassword().set("abc");*/
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((RegComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .fragmentModule(new FragmentModule(this))
                .module(new RegModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_reg);

        return view;
    }

    public static RegFragment newInstance(String someTitle) {
        RegFragment loginFragment = new RegFragment();
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

    @Override
    public void startMainActivity(int userProfileId, String token) {
        getActivity().finish();
        Intent myIntent = new Intent(getActivity(), MainActivity.class);
        myIntent.putExtra(user_profile_id_tag, userProfileId);
        myIntent.putExtra(user_token, token);
        startActivity(myIntent);
    }
}
