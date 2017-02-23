package com.damidev.dd.notregistred.base.ui;

import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivityNotRegistredBinding;
import com.damidev.dd.notregistred.base.inject.NotRegistredComponent;
import com.damidev.dd.notregistred.base.inject.NotRegistredModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.notregistred.map.ui.MapFragment;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;

import butterknife.BindView;
import butterknife.OnClick;


public class NotRegistredActivity extends D2MvvmActivity<ActivityNotRegistredBinding, NotRegistredViewModel>
        implements NotRegistredView {

    @BindView(R.id.mapa)
    Button btn;

    @BindView(R.id.ucet)
    Button ucet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_not_registred, savedInstanceState);

        replaceWithLoginFragment();
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((NotRegistredComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .activityModule(new ActivityModule(this))
                .module(new NotRegistredModule())
                .build()
                .injectMembers(this);
    }

    private void replaceWithLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        LoginFragment loginFragment = LoginFragment.newInstance(LoginFragment.LoginFragmnetTag);
        ft.replace(R.id.fragment_container, loginFragment);
        ft.commit();
    }

    private void replaceWithMapFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        MapFragment mapFragment = MapFragment.newInstance(MapFragment.MapFragmnetTag);
        ft.replace(R.id.fragment_container, mapFragment);
        ft.commit();
    }

    @OnClick(R.id.mapa)
    public void onMapClicked(final View view) {
        replaceWithMapFragment();
    }

    @OnClick(R.id.ucet)
    public void onUcetClicked(final View view) {
        replaceWithLoginFragment();
    }
}
