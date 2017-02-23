package com.damidev.core.inject;

/**
 * @author Lukas Janoska
 */

public interface ComponentBuilder<M extends D2Module, C extends D2Component> {

    ComponentBuilder<M, C> module(M module);

    C build();
}