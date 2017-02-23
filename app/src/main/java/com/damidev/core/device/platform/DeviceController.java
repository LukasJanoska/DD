package com.damidev.core.device.platform;

import android.content.Context;

import java.util.Locale;

/**
 * @author Lukas Janoska
 */

public class DeviceController {

    private final Context context;

    public DeviceController(final Context context) {
        this.context = context;
    }

    public String getLanguage() {
        return Locale.getDefault().getLanguage();
    }

}