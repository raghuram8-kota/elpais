package com.elpais.stepdefs;

import com.elpais.model.Article;
import com.elpais.pages.MainPage;
import com.elpais.utils.ConfigReader;
import com.elpais.utils.TextAnalyzer;
import com.elpais.utils.Translator;
import io.cucumber.java.Before;
import io.cucumber.java.en.*;
import org.junit.Assert;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.*;
import java.util.List;
import java.util.Map;

public class MainPageSteps {

    private final TestContext context;

    public MainPageSteps(TestContext context) {
        this.context = context;
    }

    @When("I navigate to the ElPais page")
    public void iNavigateToTheElPaisPage() {
        MainPage mainPage = new MainPage();
        mainPage.openMainPage();
    }

    @Given("I navigate to the ElPais {string} section")
    public void iNavigateToTheElPaisSection(String section) {
        MainPage mainPage = new MainPage();
        mainPage.openTab(section.toLowerCase());
    }

    @Then("I verify the page loads in spanish")
    public void iVerifyThePageLoadsInSpanish() {
        MainPage mainPage = new MainPage();
        String lang = mainPage.getPageLanguage();
        Assert.assertTrue("❌ Site is not in Spanish", lang.startsWith("es"));
    }

    @When("I fetch the top {int} articles")
    public void fetchTopArticles(int count) {
        MainPage mainPage = new MainPage();
        List<Article> fetched = mainPage.getArticles(count);
        context.setArticles(fetched);
        System.out.println("📰 Articles fetched: " + fetched.size());
    }

    @Then("I print the title and content of each article in Spanish")
    public void printArticleTitlesAndContent() {
        System.out.println("📝 Articles received in context: " + context.getArticles().size());
        for (Article article : context.getArticles()) {
            System.out.println("Title (ES): " + article.getTitle());
            System.out.println("Content:\n" + article.getContent());
            System.out.println("--------------------------------------------------\n");
        }
    }

    @Then("I download article images to my local")
    public void downloadArticleImages() {
        String imageLocation = ConfigReader.get("imagelocation");
        int count = 1;
        for (Article article : context.getArticles()) {
            String imageUrl = article.getImageUrl();
            if (imageUrl != null && !imageUrl.isEmpty()) {
                try (InputStream image = new URL(imageUrl).openStream()) {
                    Files.copy(image,
                            Paths.get(imageLocation + "/article" + count + ".jpg"),
                            StandardCopyOption.REPLACE_EXISTING);
                    System.out.println("📸 Downloaded image for Article " + count);
                } catch (Exception e) {
                    System.out.println("❌ Failed to download image for Article " + count + ": " + e.getMessage());
                }
            }
            count++;
        }
    }

    @Then("I translate the article titles to English")
    public void translateTitles() {
        for (Article article : context.getArticles()) {
            String translated = Translator.translateText(article.getTitle(), "en");
            article.setTranslatedTitle(translated);

            System.out.println("🌍 Title (ES): " + article.getTitle());
            System.out.println("🌐 Title (EN): " + translated);
            System.out.println("--------------------------------");
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
