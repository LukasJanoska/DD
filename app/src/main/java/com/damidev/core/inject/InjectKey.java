package com.damidev.core.inject;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import dagger.MapKey;

/**
 * @author Lukas Janoska
 */
@MapKey
@Retention(RetentionPolicy.RUNTIME)
public @interface InjectKey {
    Class<?> value();
}
