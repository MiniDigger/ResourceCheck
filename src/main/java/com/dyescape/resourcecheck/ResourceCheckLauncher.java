package com.dyescape.resourcecheck;

import io.vertx.core.Launcher;
import io.vertx.core.Vertx;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.intapp.vertx.guice.GuiceVerticleFactory;

import com.dyescape.resourcecheck.binder.InjectorProviderBinder;
import com.dyescape.resourcecheck.provider.InjectorProvider;

/**
 * Launcher class extending {@link Launcher}. This is done so we
 * can launch the application through the main Verticle using
 * dependency injection. This takes away the dependency injection
 * responsibility of the main Verticle. This satisfies the single
 * responsibility principle as the main Verticle should only be
 * responsible for launcher the other application Verticles and
 * start the further process.
 * @author Dennis van der Veeke - Owner & Lead Developer of Dyescape
 * @since 0.1.0
 */
public class ResourceCheckLauncher extends Launcher {

    // -------------------------------------------- //
    // LOGGER
    // -------------------------------------------- //

    private static Logger logger;

    // -------------------------------------------- //
    // DEPENDENCIES
    // -------------------------------------------- //

    @Inject private InjectorProvider injectorProvider;

    // -------------------------------------------- //
    // MAIN ACCESS POINT
    // -------------------------------------------- //

    public static void main(String[] args) {
        System.out.println();
        System.out.println(" ____");
        System.out.println("|  _ \\ _   _  ___  ___  ___ __ _ _ __   ___ ");
        System.out.println("| | | | | | |/ _ \\/ __|/ __/ _` | '_ \\ / _ \\");
        System.out.println("| |_| | |_| |  __/\\__ \\ (_| (_| | |_) |  __/");
        System.out.println("|____/ \\__, |\\___||___/\\___\\__,_| .__/ \\___|");
        System.out.println("       |___/                    |_|");
        System.out.println("  :: Resource Check ::              " +
                ResourceCheckLauncher.class.getPackage().getSpecificationVersion());
        System.out.println();

        System.setProperty(
                "vertx.logger-delegate-factory-class-name", "io.vertx.core.logging.Log4jLogDelegateFactory"
        );

        ResourceCheckLauncher.logger =  LoggerFactory.getLogger(ResourceCheckLauncher.class);
        ResourceCheckLauncher.logger.info("Set system startup properties");

        new ResourceCheckLauncher().dispatch(args);
    }

    @Override
    public void afterStartingVertx(Vertx vertx) {
        super.afterStartingVertx(vertx);

        ResourceCheckLauncher.logger.info("Creating VertX VerticleFactory");

        Injector selfInjector = Guice.createInjector(
                new InjectorProviderBinder()
        );
        selfInjector.injectMembers(this);

        GuiceVerticleFactory guiceVerticleFactory =
                new GuiceVerticleFactory(this.injectorProvider.getInjector());
        vertx.registerVerticleFactory(guiceVerticleFactory);

        ResourceCheckLauncher.logger.info("Continuing application startup using VertX");
    }
}
