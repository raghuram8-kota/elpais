package com.elpais.drivers;

import org.openqa.selenium.remote.DesiredCapabilities;

public class BrowserStackCapabilities {

    public static DesiredCapabilities desktopChrome() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("osVersion", "10");
        caps.setCapability("name", "Desktop Chrome Test");
        return caps;
    }

    public static DesiredCapabilities desktopSafari() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Safari");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("os", "OS X");
        caps.setCapability("osVersion", "Monterey");
        caps.setCapability("name", "Desktop Safari Test");
        return caps;
    }

    public static DesiredCapabilities android() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Chrome");
        caps.setCapability("device", "Samsung Galaxy S22");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "12.0");
        caps.setCapability("name", "Android Chrome Test");
        return caps;
    }

    public static DesiredCapabilities ios() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Safari");
        caps.setCapability("device", "iPhone 14");
        caps.setCapability("realMobile", "true");
        caps.setCapability("os_version", "16");
        caps.setCapability("name", "iOS Safari Test");
        return caps;
    }

    public static DesiredCapabilities edgeWindows() {
        DesiredCapabilities caps = new DesiredCapabilities();
        caps.setCapability("browserName", "Edge");
        caps.setCapability("browserVersion", "latest");
        caps.setCapability("os", "Windows");
        caps.setCapability("osVersion", "11");
        caps.setCapability("name", "Edge Windows Test");
        return caps;
    }
}
