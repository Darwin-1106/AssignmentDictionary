/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import storage.DictionaryStorage;
import java.util.*;

public class Dictionary {
      private final Map<String, String> data = new HashMap<>();
    private final DictionaryStorage storage;

    public Dictionary(DictionaryStorage storage) {
        this.storage = storage;
        storage.load(data);
    }

    public String lookup(String word) {
        return data.getOrDefault(word.toLowerCase(), "Không tìm thấy từ.");
    }

    public void addWord(String word, String meaning) {
        data.put(word.toLowerCase(), meaning);
        storage.save(data);
    }
}