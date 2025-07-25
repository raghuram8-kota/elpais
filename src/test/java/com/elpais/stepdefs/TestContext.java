package com.elpais.stepdefs;

import com.elpais.model.Article;
import java.util.List;

public class TestContext {
    private List<Article> articles;

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
