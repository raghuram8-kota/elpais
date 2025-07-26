package com.elpais.drivers;

import org.openqa.selenium.MutableCapabilities;

import java.util.HashMap;
import java.util.Map;

public class BrowserStackCapabilities {

    public static MutableCapabilities desktopChrome() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "10");
        bstackOptions.put("sessionName", "ElPais - Chrome");
        bstackOptions.put("seleniumVersion", "4.21.0");

        caps.setCapability("browserName", "Chrome");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }

    public static MutableCapabilities desktopFirefox() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("sessionName", "ElPais - Firefox");
        bstackOptions.put("seleniumVersion", "4.21.0");

        caps.setCapability("browserName", "Firefox");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }

    public static MutableCapabilities edgeWindows() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "Windows");
        bstackOptions.put("osVersion", "11");
        bstackOptions.put("sessionName", "ElPais - Edge");
        bstackOptions.put("seleniumVersion", "4.21.0");

        caps.setCapability("browserName", "Edge");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }

    public static MutableCapabilities android() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("deviceName", "Samsung Galaxy S22");
        bstackOptions.put("osVersion", "12.0");
        bstackOptions.put("realMobile", "true");
        bstackOptions.put("sessionName", "ElPais - Android");

        caps.setCapability("browserName", "Chrome");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }

    public static MutableCapabilities ios() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("deviceName", "iPhone 14");
        bstackOptions.put("osVersion", "16");
        bstackOptions.put("realMobile", "true");
        bstackOptions.put("sessionName", "ElPais - iOS");

        caps.setCapability("browserName", "Safari");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }

    // Optional simulated iOS (macOS Safari instead of real iPhone)
    public static MutableCapabilities simulatedSafari() {
        MutableCapabilities caps = new MutableCapabilities();

        Map<String, Object> bstackOptions = new HashMap<>();
        bstackOptions.put("os", "OS X");
        bstackOptions.put("osVersion", "Monterey");
        bstackOptions.put("sessionName", "ElPais - macOS Safari");
        bstackOptions.put("seleniumVersion", "4.21.0");

        caps.setCapability("browserName", "Safari");
        caps.setCapability("bstack:options", bstackOptions);
        return caps;
    }
}
