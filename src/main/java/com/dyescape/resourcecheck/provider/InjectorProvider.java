package com.dyescape.resourcecheck.provider;

import com.google.inject.Injector;

/**
 * The {@link ApplicationVerticleProvider} is used to provide you a
 * general, application wide {@link Injector} that can be used to
 * receive the instance of certain classes through Guice and allow
 * proper dependency injection.
 *
 * This interface may be mocked during testing.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public interface InjectorProvider {

    Injector getInjector();
}
