/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import java.util.List;
import java.util.Map;

public abstract class Dictionary {

    public abstract void addWord(String word, String meaning);

    public abstract String getMeaning(String word);

    public abstract List<String> getSuggestions(String prefix);

    public abstract Map<String, String> getAllWords();
}
