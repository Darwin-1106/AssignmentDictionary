/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import java.util.*;

public class SimpleDictionary extends Dictionary{
           private TreeMap<String, String> words;

    public SimpleDictionary() {
        words = new TreeMap<>();
    }

    @Override
    public void addWord(String word, String meaning) {
        words.put(word.toLowerCase(), meaning);
    }

    @Override
    public String getMeaning(String word) {
        return words.get(word.toLowerCase());
    }

    @Override
    public List<String> getSuggestions(String prefix) {
        prefix = prefix.toLowerCase();
        List<String> suggestions = new ArrayList<>();
        for (String key : words.keySet()) {
            if (key.startsWith(prefix)) {
                suggestions.add(key);
            }
        }
        return suggestions;
    }

    @Override
    public List<String> getAllWords() {
        return new ArrayList<>(words.keySet());
    }
}

