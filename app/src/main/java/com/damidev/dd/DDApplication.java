package com.damidev.dd;

import android.app.Application;
import android.content.Context;

import com.damidev.core.device.inject.DeviceModule;
import com.damidev.core.inject.ComponentBuilder;
import com.damidev.core.inject.ComponentBuilderContainer;

import java.util.Map;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * @author Lukas Janoska
 */
public class DDApplication extends Application implements ComponentBuilderContainer {

    @Inject
    Map<Class<?>, ComponentBuilder> componentBuilders;

    AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        if(BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree());
        }

        initComponents();
    }

    public void initComponents() {
        appComponent = DaggerAppComponent
                .builder()
                .appModule(new AppModule(this))
                .deviceModule(new DeviceModule(getApplicationContext()))
                .build();
        appComponent.inject(this);
    }

    public static DDApplication get(Context context) {
        return (DDApplication) context.getApplicationContext();
    }

    @Override
    public ComponentBuilder getComponentBuilder(Class<?> clazz) {
        return componentBuilders.get(clazz);
    }
}
