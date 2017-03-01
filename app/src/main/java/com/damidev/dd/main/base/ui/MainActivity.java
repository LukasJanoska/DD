package com.damidev.dd.main.base.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivityMainBinding;
import com.damidev.dd.main.account.ui.AccountFragment;
import com.damidev.dd.main.base.inject.MainComponent;
import com.damidev.dd.main.base.inject.MainModule;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;


public class MainActivity extends D2MvvmActivity<ActivityMainBinding, MainViewModel>
        implements MainView {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_main, savedInstanceState);

        replaceWithAccountFragment();
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((MainComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .activityModule(new ActivityModule(this))
                .module(new MainModule())
                .build()
                .injectMembers(this);
    }

    private void replaceWithAccountFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        AccountFragment accountFragment = AccountFragment.newInstance(AccountFragment.AccountFragmnetTag);
        ft.replace(R.id.fragment_main_container, accountFragment);
        ft.commit();
    }
}
