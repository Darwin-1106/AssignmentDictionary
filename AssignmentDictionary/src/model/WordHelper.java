/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
public class WordHelper {
//return example sentence
    public static String generateExampleSentence(String word) {
        return "This is an example sentence with the word: \"" + word + "\".";
    }
//spell each letter
    public static String spellWord(String word) {
        StringBuilder sb = new StringBuilder();
        for (char c : word.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                sb.append(c).append(" - ");
            }
        }
        if (sb.length() >= 3) {
            sb.setLength(sb.length() - 3);
        }
        return sb.toString();
    }
}

