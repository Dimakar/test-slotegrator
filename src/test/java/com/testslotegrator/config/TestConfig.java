package com.testslotegrator.config;

import org.aeonbits.owner.Config;

@Config.Sources("file:src/test/resources/testconfig.properties")
public interface TestConfig extends Config {
    String baseUrl();

    String baseUri();
}
