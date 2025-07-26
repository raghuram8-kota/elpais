package com.elpais.stepdefs;

import com.elpais.model.Article;
import com.elpais.pages.MainPage;
import com.elpais.utils.ConfigReader;
import com.elpais.utils.TextAnalyzer;
import com.elpais.utils.Translator;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

public class MainPageSteps {

    private final TestContext context;
    private final MainPage mainPage;

    public MainPageSteps(TestContext context) {
        this.context = context;
        this.mainPage = new MainPage();
    }

    @Given("I navigate to the ElPais {string} section")
    public void iNavigateToTheElPaisSection(String section) {
        mainPage.openTab(section.toLowerCase());
    }

    @When("I navigate to the ElPais page")
    public void iNavigateToTheElPaisPage() {
        mainPage.openMainPage();
    }

    @Then("I verify the page loads in spanish")
    public void iVerifyThePageLoadsInSpanish() {
        String lang = mainPage.getPageLanguage();
        Assert.assertTrue("Expected Site language : es, Actual site language: "+lang,
                lang.startsWith("es"));
    }

    @When("I fetch the top {int} articles")
    public void fetchTopArticles(int count) {
        List<Article> fetched = mainPage.getArticles(count);
        context.setArticles(fetched);
    }

    @Then("I print the title and content of each article in Spanish")
    public void printArticleTitlesAndContent() {
        for (Article article : context.getArticles()) {
            System.out.println("Title: " + article.getTitle());
            System.out.println("Content: " + article.getContent());
        }
    }

    @Then("I download article images to my local")
    public void downloadImages() {
        String imageLocation = ConfigReader.get("imagelocation");
        int count = 1;
        for (Article article : context.getArticles()) {
            String url = article.getImageUrl();
            if (url != null && !url.isEmpty()) {
                try (InputStream in = new URL(url).openStream()) {
                    Files.copy(in, Paths.get(imageLocation + "/article" + count + ".jpg"),
                            StandardCopyOption.REPLACE_EXISTING);
                } catch (Exception e) {
                    System.out.println("Failed to download image: " + e.getMessage());
                }
            }
            count++;
        }
    }

    @Then("I translate the article titles to English")
    public void translateTitles() {
        for (Article article : context.getArticles()) {
            System.out.println("article tile in spanish : "+article.getTitle());
            String translated = Translator.translateText(article.getTitle(), "en");
            System.out.println("article tile in English : "+translated);
            article.setTranslatedTitle(translated);
        }
    }

    @Then("I print the words repeated from more than {int} instance across all headers combined")
    public void analyzeTranslatedTitles(int threshold) {
        List<String> translatedTitles = context.getArticles()
                .stream()
                .map(Article::getTranslatedTitle)
                .toList();
        Map<String, Integer> freqMap = TextAnalyzer.getWordFrequency(translatedTitles);
        TextAnalyzer.printFrequentWords(freqMap, threshold);
    }
}
