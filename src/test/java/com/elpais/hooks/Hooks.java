package com.elpais.hooks;

import com.elpais.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.elpais.utils.ConfigReader;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) throws Exception {
        String runMode = ConfigReader.get("run.mode").toLowerCase();
        String browser = System.getProperty("target.browser", ConfigReader.get("browser")).toLowerCase();
        System.out.println("Running setup. Browser: " + browser + ", Run Mode: " + runMode);
        DriverManager.initializeDriver(browser, runMode);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
