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
//add words and meanings
    public abstract void addWord(String word, String meaning);
//take the meaning of the word
    public abstract String getMeaning(String word);
//Suggested words starting with prefix
    public abstract List<String> getSuggestions(String prefix);
//get the entire list from
    public abstract Map<String, String> getAllWords();
}
