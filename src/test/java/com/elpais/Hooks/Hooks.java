package com.elpais.Hooks;

import com.elpais.drivers.DriverManager;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import com.elpais.utils.ConfigReader;
import org.openqa.selenium.WebDriver;

public class Hooks {

    @Before
    public void setUp() throws Exception {
        String browser = ConfigReader.get("browser");
        String mode = ConfigReader.get("run.mode");
        System.out.println("Running setup...browser : "+browser+", run mode = "+mode);
        DriverManager.initializeDriver(browser, mode);
    }

    @After
    public void tearDown() {
        DriverManager.quitDriver();
    }
}
