package com.damidev.core.device.inject;

import android.content.Context;

import com.damidev.core.device.platform.DeviceController;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * @author Lukas Janoska
 */

@Module
public class DeviceModule {

    private Context context;

    public DeviceModule(Context context) {
        this.context = context;
    }

    @Singleton
    @Provides
    DeviceController provideController() {
        return new DeviceController(context);
    }
}