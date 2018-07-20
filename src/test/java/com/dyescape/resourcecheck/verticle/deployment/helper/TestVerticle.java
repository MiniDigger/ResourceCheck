package com.dyescape.resourcecheck.verticle.deployment.helper;

import io.vertx.core.AbstractVerticle;

public class TestVerticle extends AbstractVerticle {

    public static final String DEPLOYED_VERTICLE_ADDRESS = "test_verticle_deployed";

    @Override
    public void start() {
        this.getVertx().eventBus().publish(DEPLOYED_VERTICLE_ADDRESS, this.getClass().getSimpleName());
    }
}
