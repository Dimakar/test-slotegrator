package com.testslotegrator.config;

import org.aeonbits.owner.Config;

@Config.LoadPolicy(Config.LoadType.MERGE)
@Config.Sources({"system:properties", "system:env", "file:src/test/resources/testconfig.properties"})
public interface TestConfig extends Config {
    String baseUrl();

    @DefaultValue("chrome")
    String browser();

    String baseUri();
}
