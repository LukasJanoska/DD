package com.damidev.core.inject;

/**
 * @author Lukas Janoska
 */
public interface ComponentBuilderContainer {

    ComponentBuilder getComponentBuilder(Class<?> clazz);
}