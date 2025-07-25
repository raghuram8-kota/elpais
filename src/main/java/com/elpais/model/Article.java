package com.elpais.model;

public class Article {
    private String title;
    private String imageUrl;
    private String content;
    private String translatedTitle;


    public Article(String title, String imageUrl, String content) {
        this.title = title;
        this.imageUrl = imageUrl;
        this.content = content;
    }

    public String getTitle() { return title; }
    public String getImageUrl() { return imageUrl; }
    public String getContent() { return content; }

    public String getTranslatedTitle() {return translatedTitle;}
    public void setTranslatedTitle(String translatedTitle) {
        this.translatedTitle = translatedTitle;
    }

}

