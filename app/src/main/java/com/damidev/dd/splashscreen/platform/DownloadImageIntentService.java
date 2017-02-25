package com.damidev.dd.splashscreen.platform;


import android.app.IntentService;
import android.content.Intent;

public class DownloadImageIntentService extends IntentService {

    public DownloadImageIntentService() {
        super("asada");
    }

    @Override
    protected void onHandleIntent(Intent workIntent) {
        // Gets data from the incoming Intent
        String dataString = workIntent.getDataString();
        // Do work here, based on the contents of dataString
    }
}
