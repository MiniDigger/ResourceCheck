package com.dyescape.resourcecheck;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.CompositeFuture;
import io.vertx.core.Future;
import io.vertx.core.Verticle;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import com.google.inject.Inject;

import com.dyescape.resourcecheck.provider.ApplicationVerticleProvider;
import com.dyescape.resourcecheck.provider.InjectorProvider;

/**
 * Main startup Verticle of the Resource Check, responsible for
 * the startup process of the application, such as deploying all
 * other application Verticles and making sure all are working as
 * they should.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public final class ResourceCheck extends AbstractVerticle {

    // -------------------------------------------- //
    // CONSTANTS
    // -------------------------------------------- //

    public static final String DEPLOYED_ADDRESS = "resource_check_startup_address";

    // -------------------------------------------- //
    // LOGGER
    // -------------------------------------------- //

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    private final InjectorProvider injectorProvider;
    private final ApplicationVerticleProvider applicationVerticleProvider;

    // -------------------------------------------- //
    // INSTANCE & CONSTRUCT
    // -------------------------------------------- //

    @Inject
    public ResourceCheck(InjectorProvider injectorProvider,
                               ApplicationVerticleProvider applicationVerticleProvider) {
        this.injectorProvider = injectorProvider;
        this.applicationVerticleProvider = applicationVerticleProvider;
    }

    // -------------------------------------------- //
    // OVERRIDE
    // -------------------------------------------- //

    @Override
    public void start(Future<Void> future) {
        List<Future> deploymentFutures = new ArrayList<>();

        for (Class<? extends Verticle> clazz : applicationVerticleProvider.getApplicationVerticles()) {
            Future deploymentFuture = Future.future();
            this.getVertx().deployVerticle(this.injectorProvider.getInjector().getInstance(clazz), deploymentFuture);
        }

        CompositeFuture.all(deploymentFutures).setHandler(deploymentHandler -> {
            this.logger.debug(String.format("Publishing startup event on %s", DEPLOYED_ADDRESS));
            this.getVertx().eventBus().publish(DEPLOYED_ADDRESS, null);
            future.complete();
        });
    }
}
