package com.dyescape.resourcecheck.binder;

import javax.inject.Inject;

import com.google.inject.Guice;
import com.google.inject.Injector;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.dyescape.resourcecheck.provider.InjectorProvider;
import com.dyescape.resourcecheck.provider.InjectorProviderImpl;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("When starting the application with the InjectorProviderBinder")
public class WhenStartingTheApplicationWithInjectorProviderBinderTest {

    @Inject
    private InjectorProvider injectorProvider;

    @BeforeEach
    public void setup() {
        Injector injector = Guice.createInjector(new InjectorProviderBinder());
        injector.injectMembers(this);
    }

    @Test
    @DisplayName("It should inject ApplicationVerticleProvider instances")
    public void itShouldInjectApplicationInjectorProviderInstances() {
        assertNotNull(this.injectorProvider);
    }

    @Test
    @DisplayName("It should use the correct implementation")
    public void itShouldUseTheCorrectImplementation() {
        assertTrue(this.injectorProvider instanceof InjectorProviderImpl);
    }
}
