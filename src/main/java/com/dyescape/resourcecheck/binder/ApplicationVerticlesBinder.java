package com.dyescape.resourcecheck.binder;

import com.google.inject.AbstractModule;

import com.dyescape.resourcecheck.provider.ApplicationVerticleProvider;
import com.dyescape.resourcecheck.provider.ApplicationVerticleProviderImpl;

/**
 * ApplicationVerticlesBinder class as extension of Guice's AbstractModule which
 * is used to bind the {@link ApplicationVerticleProvider} interface to the
 * implementation of {@link ApplicationVerticleProviderImpl} when injecting the
 * {@link ApplicationVerticleProvider} interface.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public class ApplicationVerticlesBinder extends AbstractModule {

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    protected void configure() {
        this.bind(ApplicationVerticleProvider.class).to(ApplicationVerticleProviderImpl.class);
    }
}
