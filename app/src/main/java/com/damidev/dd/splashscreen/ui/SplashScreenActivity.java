package com.damidev.dd.splashscreen.ui;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

import com.damidev.core.inject.ComponentBuilderContainer;
import com.damidev.dd.R;
import com.damidev.dd.databinding.ActivitySplashScreenBinding;
import com.damidev.dd.notregistred.base.ui.NotRegistredActivity;
import com.damidev.dd.notregistred.map.ui.MapFragment;
import com.damidev.dd.shared.inject.ActivityModule;
import com.damidev.dd.shared.inject.D2MvvmActivity;
import com.damidev.dd.splashscreen.Events.ServerEvent;
import com.damidev.dd.splashscreen.dataaccess.ServerMapResponseDto;
import com.damidev.dd.splashscreen.inject.SplashScreenComponent;
import com.damidev.dd.splashscreen.inject.SplashScreenModule;
import com.damidev.dd.splashscreen.platform.BusProvider;
import com.damidev.dd.splashscreen.platform.DownloadImageIntentService;
import com.damidev.dd.splashscreen.platform.MapCommunicator;
import com.squareup.otto.Subscribe;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Timer;
import java.util.TimerTask;


public class SplashScreenActivity extends D2MvvmActivity<ActivitySplashScreenBinding, SplashScreenViewModel>
        implements SplashScreenView {

    final long Delay = 2000;
    Timer RunSplash = new Timer();
    private MapCommunicator communicator;

    boolean permissionCheck = false;
    private static final int MY_PERMISSIONS_REQUEST = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setAndBindContentView(R.layout.activity_splash_screen, savedInstanceState);

        if (ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED) {
            RunSplash = new Timer();
            RunSplash.schedule(getShowSplashTimer(), Delay);

            communicator = new MapCommunicator();
            usePost();

            Intent mServiceIntent = new Intent(this, DownloadImageIntentService.class);
            mServiceIntent.setData(Uri.parse("download"));

            getApplicationContext().startService(mServiceIntent);

            downloadImage();

        } else {
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    MY_PERMISSIONS_REQUEST);
            permissionCheck = true;
        }

    }

    public void downloadImage() {
        Picasso.with(this)
                .load(MapFragment.ImageUrl)
                //.placeholder(R.drawable.new)
                .into(new Target() {
                          @Override
                          public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                              try {
                                  // Assume block needs to be inside a Try/Catch block.
                                  String path = Environment.getExternalStorageDirectory().toString();
                                  OutputStream fOut = null;
                                  Integer counter = 0;
                                  File file = new File(path, "FitnessGirl"+counter+".jpg"); // the File to save , append increasing numeric counter to prevent files from getting overwritten.
                                  fOut = new FileOutputStream(file);

                                  bitmap.compress(Bitmap.CompressFormat.JPEG, 85, fOut); // saving the Bitmap to a file compressed as a JPEG with 85% compression rate
                                  fOut.flush(); // Not really required
                                  fOut.close(); // do not forget to close the stream

                                  MediaStore.Images.Media.insertImage(getContentResolver(), file.getAbsolutePath(), file.getName(), file.getName());
                              } catch(Exception e){
                                  e.getMessage();
                                  // some action
                              }
                          }

                          @Override
                          public void onBitmapFailed(Drawable errorDrawable) {
                          }

                          @Override
                          public void onPrepareLoad(Drawable placeHolderDrawable) {
                          }
                      }
                );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    RunSplash = new Timer();
                    RunSplash.schedule(getShowSplashTimer(), Delay);

                    communicator = new MapCommunicator();
                    usePost();
                }else{
                    Toast.makeText(getApplicationContext(), "permission denied", Toast.LENGTH_LONG).show();
                    //findViewById(R.id.ProgressBar).setVisibility(View.INVISIBLE);
                    //findViewById(R.id.permissionsDenied).setVisibility(View.VISIBLE);
                }
            }
        }
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
        RunSplash.cancel();
        if(!permissionCheck)
            finish();
    }
}