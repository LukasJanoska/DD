package com.damidev.core.network.platform;

import android.Manifest;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.RequiresPermission;

import javax.inject.Singleton;

import timber.log.Timber;

/**
 * @author Lukas Janoska
 */

@Singleton
public class NetworkController {

    private final ConnectivityManager connectivityManager;

    public NetworkController(ConnectivityManager connectivityManager) {
        this.connectivityManager = connectivityManager;
    }

    @RequiresPermission(allOf = { Manifest.permission.INTERNET, Manifest.permission.ACCESS_NETWORK_STATE})
    public boolean isNetworkConnectionAvailable() {
        final NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        }

        Timber.d("No network connection available");
        return false;
    }
}
