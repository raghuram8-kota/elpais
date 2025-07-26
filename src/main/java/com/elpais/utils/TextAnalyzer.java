package com.elpais.utils;

import java.util.*;

public class TextAnalyzer {

    public static Map<String, Integer> getWordFrequency(List<String> lines) {
        Map<String, Integer> freqMap = new HashMap<>();
        for (String line : lines) {
            if (line == null) continue;
            // Remove punctuation and convert to lowercase
            String cleaned = line.replaceAll("[^a-zA-Z ]", "").toLowerCase();
            String[] words = cleaned.split("\\s+");
            for (String word : words) {
                if (word.isBlank()) continue;
                freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
            }
        }
        return freqMap;
    }

    public static void printFrequentWords(Map<String, Integer> freqMap, int threshold) {
        System.out.println("Words repeated more than " + threshold + " times:");
        boolean found = false;
        for (Map.Entry<String, Integer> entry : freqMap.entrySet()) {
            if (entry.getValue() > threshold) {
                System.out.println(entry.getKey() + " → " + entry.getValue());
                found = true;
            }
        }
        if (!found) {
            System.out.println("No words found with frequency greater than " + threshold);
        }
    }

}
