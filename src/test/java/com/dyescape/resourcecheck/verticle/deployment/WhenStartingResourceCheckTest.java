package com.dyescape.resourcecheck.verticle.deployment;

import java.util.ArrayList;
import java.util.List;

import io.vertx.core.Verticle;
import io.vertx.core.Vertx;
import io.vertx.junit5.VertxExtension;
import io.vertx.junit5.VertxTestContext;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;

import com.dyescape.resourcecheck.ResourceCheck;
import com.dyescape.resourcecheck.binder.ApplicationVerticlesBinder;
import com.dyescape.resourcecheck.binder.InjectorProviderBinder;
import com.dyescape.resourcecheck.provider.ApplicationVerticleProvider;
import com.dyescape.resourcecheck.provider.InjectorProvider;
import com.dyescape.resourcecheck.verticle.deployment.helper.TestVerticle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(VertxExtension.class)
@DisplayName("When starting Resource Check")
public class WhenStartingResourceCheckTest {

    private static Injector injector;

    @BeforeAll
    public static void setup() {
        Verticle testVerticle = new TestVerticle();

        List<Class<? extends Verticle>> verticles = new ArrayList<>();
        verticles.add(testVerticle.getClass());

        injector = Guice.createInjector(
                new ApplicationVerticlesBinder(),
                new InjectorProviderBinder()
        );
    }

    @Test
    @DisplayName("It should complete the startup future successfully")
    public void itShouldCompleteTheStartupFutureSuccessfully(Vertx vertx, VertxTestContext testContext) {
        ApplicationVerticleProvider applicationVerticleProvider = Mockito.mock(ApplicationVerticleProvider.class);
        Mockito.when(applicationVerticleProvider.getApplicationVerticles()).thenReturn(new ArrayList<>());

        ResourceCheck resourceCheck = new ResourceCheck(injector.getInstance(InjectorProvider.class),
                applicationVerticleProvider);
        vertx.deployVerticle(resourceCheck, (handler) -> {
            assertTrue(handler.succeeded());
            testContext.completeNow();
        });
    }

    @Test
    @DisplayName("It should deploy the provided list of Verticles")
    public void itShouldDeployTheProvidedListOfVerticles(Vertx vertx, VertxTestContext testContext) {
        List<Class<? extends Verticle>> verticles = new ArrayList<>();
        verticles.add(TestVerticle.class);

        ApplicationVerticleProvider applicationVerticleProvider = Mockito.mock(ApplicationVerticleProvider.class);
        Mockito.when(applicationVerticleProvider.getApplicationVerticles()).thenReturn(verticles);

        ResourceCheck resourceCheck = new ResourceCheck(injector.getInstance(InjectorProvider.class),
                applicationVerticleProvider);

        vertx.eventBus().consumer(TestVerticle.DEPLOYED_VERTICLE_ADDRESS, handler -> {
            assertEquals(handler.body(), TestVerticle.class.getSimpleName());
            testContext.completeNow();
        });

        vertx.deployVerticle(resourceCheck);
    }
}
