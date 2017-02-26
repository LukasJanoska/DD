package com.damidev.dd.splashscreen.platform;


import android.app.IntentService;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.provider.MediaStore;

import com.damidev.dd.notregistred.map.ui.MapFragment;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import timber.log.Timber;

public class DownloadImageIntentService extends IntentService {

    public DownloadImageIntentService() {
        super("asada");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        if(workIntent.getData() != null) {
            Timber.d("" + workIntent.getData().toString());

            downloadImage();
        }

        String dataString = workIntent.getDataString();
        // Do work here, based on the contents of dataString
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

}
