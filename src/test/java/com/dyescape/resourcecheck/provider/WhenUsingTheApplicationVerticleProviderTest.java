package com.dyescape.resourcecheck.provider;

import io.vertx.junit5.VertxExtension;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(VertxExtension.class)
@DisplayName("When using the ApplicationVerticleProvider")
public class WhenUsingTheApplicationVerticleProviderTest {

    @Test
    @DisplayName("It should return a list of Verticles to startup during bootstrap")
    public void itShouldReturnAListOfVerticlesToStartupDuringBootstrap() {
        ApplicationVerticleProvider databaseConnectionProvider = new ApplicationVerticleProviderImpl();
        assertNotNull(databaseConnectionProvider.getApplicationVerticles());
    }
}
