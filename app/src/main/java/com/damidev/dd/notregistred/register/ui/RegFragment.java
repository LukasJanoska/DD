package com.damidev.dd.notregistred.register.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.FragmentRegBinding;
import com.damidev.dd.notregistred.register.inject.RegComponent;
import com.damidev.dd.notregistred.register.inject.RegModule;
import com.damidev.dd.shared.inject.D2MvvmFragment;


public class RegFragment extends D2MvvmFragment<FragmentRegBinding, RegViewModel>
        implements RegView {

    public static String RegFragmnetTag = "REG_FRAGMENT_TAG";

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


}
