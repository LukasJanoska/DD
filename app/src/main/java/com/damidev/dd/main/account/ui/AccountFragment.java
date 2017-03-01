package com.damidev.dd.main.account.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentAccountBinding;
import com.damidev.dd.main.account.inject.AccountComponent;
import com.damidev.dd.main.account.inject.AccountModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;


public class AccountFragment extends D2MvvmFragment<FragmentAccountBinding, AccountViewModel>
        implements AccountView {

    public static String AccountFragmnetTag = "ACCOUNT_FRAGMENT_TAG";

    public AccountFragment() {
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
        ((AccountComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .module(new AccountModule())
                .build()
                .injectMembers(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = setAndBindContentView(inflater, container, R.layout.fragment_account);

        return view;
    }

    public static AccountFragment newInstance(String someTitle) {
        AccountFragment loginFragment = new AccountFragment();
        return loginFragment;
    }


}
