package com.elpais.pages;

import com.elpais.utils.ConfigReader;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected final int DEFAULT_TIMEOUT = ConfigReader.getInt("default.timeout");

    public BasePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement waitForVisibility(By locator) {
        return new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public void click(By locator) {
        try {
            waitForVisibility(locator).click();
        } catch (ElementClickInterceptedException e) {
            System.out.println("Click intercepted, attempting JavaScript click...");
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(locator));
        } catch (Exception e) {
            throw new RuntimeException("Failed to click element: " + locator, e);
        }
    }

    public void type(By locator, String text) {
        WebElement element = waitForVisibility(locator);
        element.clear();
        element.sendKeys(text);
    }

    public void scrollIntoView(By locator) {
        WebElement element = waitForVisibility(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public boolean isElementPresent(By locator) {
        try {
            driver.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void waitForElement(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(DEFAULT_TIMEOUT));
        wait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
