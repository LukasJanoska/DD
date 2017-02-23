package com.damidev.dd.splashscreen.ui;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivitySplashScreenBinding;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;
import com.damidev.dd.splashscreen.Events.ServerEvent;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;
import com.damidev.dd.splashscreen.inject.SplashScreenComponent;
import com.damidev.dd.splashscreen.inject.SplashScreenModule;
import com.damidev.dd.splashscreen.platform.BusProvider;
import com.damidev.dd.splashscreen.platform.MapCommunicator;
import com.squareup.otto.Subscribe;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends D2MvvmActivity<ActivitySplashScreenBinding, SplashScreenViewModel>
        implements SplashScreenView {

    final long Delay = 2000;
    Timer RunSplash = new Timer();
    private MapCommunicator communicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_splash_screen, savedInstanceState);

        RunSplash = new Timer();
        RunSplash.schedule(getShowSplashTimer(), Delay);

        communicator = new MapCommunicator();
        usePost();
    }

    private void usePost(){
        communicator.getMapPoints();
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

    @Subscribe
    public void onServerEvent(ServerEvent serverEvent){
        Toast.makeText(this, ""+serverEvent.getServerResponse().getResponseCodeText(), Toast.LENGTH_SHORT).show();

        writeObjectToFile(getApplicationContext(), serverEvent.getServerResponse());

        /*if(serverEvent.getServerResponse().getUsername() != null){
            information.setText("Username: "+serverEvent.getServerResponse().getUsername() + " || Password: "+serverEvent.getServerResponse().getPassword());
        }*/
        //extraInformation.setText("" + serverEvent.getServerResponse().getMessage());
    }


    public boolean writeObjectToFile(Context context, ServerMapResponseDto serverMapResponseDto) {
        try {
            FileOutputStream fos = context.openFileOutput("ServerMapResponseDto.srl", MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(serverMapResponseDto);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getInstance().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        BusProvider.getInstance().unregister(this);
    }
}