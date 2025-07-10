/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

/**
 *
 * @author Lenovo
 */
import model.Dictionary;
import java.io.*;
import java.util.Map;

public class IndexedFileDictionaryStorage implements DictionaryStorage {

    private final String filePath;

    public IndexedFileDictionaryStorage(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void load(Dictionary dictionary) {
        try (BufferedReader reader = new BufferedReader(new FileReader(this.filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf(":");
                if (index != -1) {
                    String word = line.substring(0, index).trim();
                    String meaning = line.substring(index + 1).trim();
                    dictionary.addWord(word, meaning);
                }
            }
        } catch (IOException e) {
            System.err.println("[LOAD ERROR] " + e.getMessage());
        }
    }

    @Override
    public void save(Dictionary dictionary) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(this.filePath))) {
            for (Map.Entry<String, String> entry : dictionary.getAllWords().entrySet()) {
                writer.write(entry.getKey() + ":" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("[SAVE ERROR] " + e.getMessage());
        }
    }

    @Override
    public Dictionary load() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
