/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

/**
 *
 * @author Lenovo
 */
import java.io.*;
import java.util.HashMap;
import java.util.Map;

//load data from file into map
public class ExampleSentenceStorage {
     private final String filePath;
    private Map<String, String> exampleMap = new HashMap<>();

    public ExampleSentenceStorage(String filePath) {
        this.filePath = filePath;
        loadExamples();
    }

//add to map
    private void loadExamples() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                int index = line.indexOf("::");
                if (index != -1) {
                    String word = line.substring(0, index).trim();
                    String sentence = line.substring(index + 2).trim();
                    exampleMap.put(word, sentence);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc ví dụ: " + e.getMessage());
        }
    }

//return example
    public String getExample(String word) {
        return exampleMap.getOrDefault(word, "Chưa có ví dụ cho từ này.");
    }

    public void addExample(String word, String sentence) {
        exampleMap.put(word, sentence);
        saveExamples();
    }

    private void saveExamples() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Map.Entry<String, String> entry : exampleMap.entrySet()) {
                writer.write(entry.getKey() + "::" + entry.getValue());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi lưu ví dụ: " + e.getMessage());
        }
    }
}
