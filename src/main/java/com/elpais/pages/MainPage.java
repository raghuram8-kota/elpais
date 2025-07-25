package com.elpais.pages;

import com.elpais.drivers.DriverManager;
import com.elpais.utils.TextAnalyzer;
import io.cucumber.java.en.Then;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.elpais.utils.ConfigReader;
import com.elpais.model.Article;



import java.time.Duration;
import java.util.*;

public class  MainPage extends BasePage {

    public MainPage() {
        super(DriverManager.getDriver());
    }

    public void openMainPage() {
        driver.get(ConfigReader.get("base.url"));
        acceptCookiesIfPresent();
    }

    public void acceptCookiesIfPresent() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            WebElement acceptButton = wait.until(ExpectedConditions.elementToBeClickable(
                    By.id("didomi-notice-agree-button")));
            acceptButton.click();
            System.out.println("Cookie banner accepted.");
        }catch (Exception e) {
            System.out.println("Failed to click cookie banner: " + e.getMessage());
        }
    }

    public String getPageLanguage() {
        return driver.findElement(By.tagName("html")).getAttribute("lang");
    }

    public void openTab(String sectionPath) {
        driver.get(ConfigReader.get("base.url") + sectionPath);
        acceptCookiesIfPresent();
    }

    public List<Article> getArticles(int limit) {
        List<Article> articles = new ArrayList<>();
        List<WebElement> articleElements = driver.findElements(By.cssSelector("section[data-dtm-region='portada_apertura'] article"));
        int count = Math.min(limit, articleElements.size());

        for (int i = 0; i < count; i++) {
            try {
                WebElement articleEl = articleElements.get(i);
                WebElement titleElement = articleEl.findElement(By.cssSelector("h2.c_t > a"));
                String title = titleElement.getText().trim();
                WebElement summaryElement = articleEl.findElement(By.cssSelector("p.c_d"));
                String content = summaryElement.getText().trim();

                // Image URL (if available)
                String imageUrl = "";
                try {
                    WebElement imgElement = articleEl.findElement(By.cssSelector("figure img"));
                    String srcSet = imgElement.getAttribute("srcset");
                    if (srcSet != null && !srcSet.isEmpty()) {
                        imageUrl = srcSet.split("&")[0]; // First image URL
                        System.out.println("imageUrl found for article" + (i + 1)+": "+imageUrl);
                    }
                } catch (Exception e) {
                    System.out.println("No image found for article " + (i + 1)+ ": " + e.getMessage());
                }
                articles.add(new Article(title, imageUrl, content));
            } catch (Exception e) {
                System.out.println("Error extracting article " + (i + 1) + ": " + e.getMessage());
            }
        }

        return articles;
    }

}
