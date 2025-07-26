package com.elpais.Hooks;

import com.elpais.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.elpais.utils.ConfigReader;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws Exception {
        String browser = ConfigReader.get("browser").toLowerCase();
        String runMode = ConfigReader.get("run.mode").toLowerCase();

        if (scenario.getSourceTagNames().contains("@firefox")) browser = "firefox";
        if (scenario.getSourceTagNames().contains("@edge")) browser = "edge";
        if (scenario.getSourceTagNames().contains("@android")) browser = "android";
        if (scenario.getSourceTagNames().contains("@ios")) browser = "ios";

        System.out.println("Running setup...browser : "+browser+", run mode = "+runMode);
        DriverManager.initializeDriver(browser, runMode);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
