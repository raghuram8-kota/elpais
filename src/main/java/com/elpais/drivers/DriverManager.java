package com.elpais.drivers;

import com.elpais.utils.ConfigReader;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;


import java.net.URL;
import java.util.Objects;

public class DriverManager {
    private static final ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    public static void initializeDriver(String browser, String mode) throws Exception {
        try {
            if (mode.equals("browserstack")) {
                String username = ConfigReader.get("bs.username");
                String accessKey = ConfigReader.get("bs.accesskey");
                String remoteURL = "https://" + username + ":" + accessKey + "@hub-cloud.browserstack.com/wd/hub";
                System.out.println("Connecting to: " + remoteURL);

                MutableCapabilities caps = switch (browser) {
                    case "chrome" -> BrowserStackCapabilities.desktopChrome();
                    case "firefox" -> BrowserStackCapabilities.desktopFirefox();
                    case "edge"   -> BrowserStackCapabilities.edgeWindows();
                    case "android" -> BrowserStackCapabilities.android();
                    case "ios"     -> BrowserStackCapabilities.ios();
                    default -> throw new RuntimeException("Unsupported browser: " + browser);
                };

                System.out.println("Connecting to BrowserStack with:");
                System.out.println("URL: " + remoteURL);
                System.out.println("Caps: " + caps.toString());

                driver.set(new RemoteWebDriver(new URL(remoteURL), caps));
            } else { // local
                if (Objects.equals(browser, "chrome")) {
                    System.setProperty("webdriver.chrome.driver", "D:/POCS/elpais/src/main/java/com/elpais/drivers/chromedriver.exe");
                    driver.set(new ChromeDriver());
                }else {
                    throw new RuntimeException("Unsupported local browser: " + browser);
                }
            }
            driver.get().manage().window().maximize();
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize WebDriver: " + e.getMessage());
        }
    }

    public static WebDriver getDriver() {
        WebDriver d = driver.get();
        System.out.println("DriverManager.getDriver() returning: " + d);
        return d;
    }

    public static void quitDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }
}
