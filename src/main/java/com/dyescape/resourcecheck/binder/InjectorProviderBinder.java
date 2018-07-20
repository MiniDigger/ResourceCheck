package com.dyescape.resourcecheck.binder;

import com.google.inject.AbstractModule;

import com.dyescape.resourcecheck.provider.InjectorProvider;
import com.dyescape.resourcecheck.provider.InjectorProviderImpl;


/**
 * InjectorProviderBinder class as extension of Guice's AbstractModule which
 * is used to bind the {@link InjectorProvider} interface to the
 * implementation of {@link InjectorProviderImpl} when injecting the
 * {@link InjectorProvider} interface.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public class InjectorProviderBinder extends AbstractModule {

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    protected void configure() {
        this.bind(InjectorProvider.class).to(InjectorProviderImpl.class);
    }
}
