package com.damidev.core.network.inject;

import android.content.Context;
import android.net.ConnectivityManager;

import com.damidev.core.network.platform.NetworkController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Lukas Janoska
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    ConnectivityManager provideConnectivityManager(final Context context) {
        return (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
    }

    @Provides
    @Singleton
    NetworkController provideNetworkController(final ConnectivityManager connectivityManager) {
        return new NetworkController(connectivityManager);
    }
}