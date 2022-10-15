package com.testslotegrator.stepdefs;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.WebDriverRunner;
import com.testslotegrator.config.TestConfig;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.aeonbits.owner.ConfigFactory;

public class UiTestHooks {
    @Before
    public void beforeTest() {
        TestConfig testConfig = ConfigFactory.create(TestConfig.class);
        Configuration.baseUrl = testConfig.baseUrl();
        Configuration.browser = testConfig.browser();
        Configuration.timeout = 2000;
    }

    @After
    public void tearDown() {
        Selenide.clearBrowserLocalStorage();
        Selenide.clearBrowserCookies();
        WebDriverRunner.clearBrowserCache();
    }
}
