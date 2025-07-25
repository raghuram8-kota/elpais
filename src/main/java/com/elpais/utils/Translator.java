package com.elpais.utils;

import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class Translator {

    private static final String API_URL = ConfigReader.get("rapidapi.url");
    private static final String API_KEY = ConfigReader.get("rapidapi.key");
    private static final String API_HOST = ConfigReader.get("rapidapi.host");

    public static String translateText(String text, String targetLang) {
        Map<String, Object> body = new HashMap<>();
        body.put("q", text);
        body.put("from", "es");
        body.put("to", targetLang);

        Response response = RestAssured
                .given()
                .header("Content-Type", "application/json")
                .header("X-RapidAPI-Key", API_KEY)
                .header("X-RapidAPI-Host", API_HOST)
                .body(body)
                .post(API_URL);

        if (response.statusCode() == 200) {
            return response.jsonPath().getString("[0]");
        } else {
            return "[Translation Failed]";
        }
    }
}
