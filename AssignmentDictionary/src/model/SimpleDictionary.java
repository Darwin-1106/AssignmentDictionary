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

public class SimpleDictionary extends Dictionary {
//word structure and meaning

    private final TreeMap<String, String> words;

    public SimpleDictionary() {
        words = new TreeMap<>();
    }

    @Override
//add word, convert to lowercase and add meaning
    public void addWord(String word, String meaning) {
        words.put(word.toLowerCase(), meaning);
    }

    @Override
//return the meaning of the word
    public String getMeaning(String word) {
        return words.get(word.toLowerCase());
    }

    @Override
//add words starting with prefix
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
//return the entire dictionary
    public Map<String, String> getAllWords() {
        return words; // 'words' là TreeMap<String, String>
    }
}
