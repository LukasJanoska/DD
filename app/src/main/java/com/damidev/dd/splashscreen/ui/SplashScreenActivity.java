package com.damidev.dd.splashscreen.ui;

import android.content.Intent;
import android.os.Bundle;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivitySplashScreenBinding;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.shared.inject.D2MvvmActivity;
import com.damidev.dd.splashscreen.inject.SplashScreenComponent;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.splashscreen.inject.SplashScreenModule;

import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends D2MvvmActivity<ActivitySplashScreenBinding, SplashScreenViewModel>
        implements SplashScreenView {

    final long Delay = 5000;
    Timer RunSplash = new Timer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_splash_screen, savedInstanceState);

        RunSplash = new Timer();
        RunSplash.schedule(getShowSplashTimer(), Delay);
    }

    @Override
    protected void setupComponent(ComponentBuilderContainer componentBuilder) {
        ((SplashScreenComponent.Builder) componentBuilder.getComponentBuilder(this.getClass()))
                .activityModule(new ActivityModule(this))
                .module(new SplashScreenModule())
                .build()
                .injectMembers(this);
    }

    private TimerTask getShowSplashTimer(){
        return new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent myIntent = new Intent(SplashScreenActivity.this, NotRegistredActivity.class);
                startActivity(myIntent);
            }
        };
    }
}