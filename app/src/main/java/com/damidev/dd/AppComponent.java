package com.damidev.dd;

import com.damidev.core.device.inject.DeviceModule;
import com.damidev.dd.shared.inject.ActivityBindingModule;
import com.damidev.dd.shared.inject.FragmentBindingModule;
import com.damidev.dd.shared.rest.inject.RestModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(
        modules = {
                AppModule.class,
                ActivityBindingModule.class,
                FragmentBindingModule.class,
                RestModule.class,
                DeviceModule.class
//                NetworkModule.class
        }
)
public interface AppComponent {
    DDApplication inject(DDApplication application);
}
