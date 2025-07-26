package com.elpais.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = {"com.elpais", "hooks"},
        plugin = {"pretty", "html:target/chrome-report"},
        tags = "@ios"
)
public class iOSTest {
    static {
        System.setProperty("target.browser", "ios");
    }
}
