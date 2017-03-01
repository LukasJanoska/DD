package com.damidev.dd.main.base.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivityMainBinding;
import com.damidev.dd.main.base.inject.MainComponent;
import com.damidev.dd.main.base.inject.MainModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;


public class MainActivity extends D2MvvmActivity<ActivityMainBinding, MainViewModel>
        implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_main, savedInstanceState);

        //replaceWithLoginFragment();
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((MainComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .activityModule(new ActivityModule(this))
                .module(new MainModule())
                .build()
                .injectMembers(this);
    }

    private void replaceWithLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = LoginFragment.newInstance(LoginFragment.LoginFragmnetTag);
        ft.replace(R.id.fragment_container, loginFragment);
        ft.commit();
    }
}
