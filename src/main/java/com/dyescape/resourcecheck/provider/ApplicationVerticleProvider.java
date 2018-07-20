package com.dyescape.resourcecheck.provider;

import java.util.List;

import io.vertx.core.Verticle;

/**
 * The {@link ApplicationVerticleProvider} is used to retrieve a list
 * of Verticles. These Verticles must be deployed when the application
 * is deployed as they build up the main application functionality.
 *
 * This interface may be mocked during testing.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public interface ApplicationVerticleProvider {

    List<Class<? extends Verticle>> getApplicationVerticles();
}
