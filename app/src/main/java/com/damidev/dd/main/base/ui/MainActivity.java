package com.damidev.dd.main.base.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivityMainBinding;
import com.damidev.dd.main.account.contacts.ui.ContactsFragment;
import com.damidev.dd.main.account.profile.ui.ProfileFragment;
import com.damidev.dd.main.base.inject.MainComponent;
import com.damidev.dd.main.base.inject.MainModule;
import com.damidev.dd.notregistred.login.ui.LoginFragment;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;

import butterknife.BindView;


public class MainActivity extends D2MvvmActivity<ActivityMainBinding, MainViewModel>
        implements NavigationView.OnNavigationItemSelectedListener, MainView {

    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    private int userProfileId;
    private String token;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_main, savedInstanceState);

        Intent mIntent = getIntent();
        userProfileId = mIntent.getIntExtra(LoginFragment.user_profile_id_tag, 0);
        token = mIntent.getStringExtra(LoginFragment.user_token);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });

        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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
        ProfileFragment accountFragment = ProfileFragment.newInstance(ProfileFragment.AccountFragmnetTag, userProfileId);
        ft.replace(R.id.fragment_main_container, accountFragment);
        ft.commit();
        toolbar.setTitle("YOUR PROFILE");
        saveStringPreferences("token", token);
    }

    private void replaceWithContactsFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ContactsFragment contactsFragment = ContactsFragment.newInstance(ContactsFragment.ContactsFragmnetTag, token);
        ft.replace(R.id.fragment_main_container, contactsFragment);
        ft.commit();
        toolbar.setTitle("CONTACTS");
        saveStringPreferences("token", token);
    }

    public void saveStringPreferences(String key, String value) {
        SharedPreferences prefs = getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        prefs.edit()
                .putString(key, value)
                .apply();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            replaceWithAccountFragment();
        } else if (id == R.id.contacts) {
            replaceWithContactsFragment();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
