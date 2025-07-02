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
import model.SimpleDictionary;

import java.io.*;
import java.util.Scanner;
public class IndexedFileDictionaryStorage implements DictionaryStorage {

      private File file;

    public IndexedFileDictionaryStorage(String filePath) {
        this.file = new File(filePath);
    }

    @Override
    public Dictionary load() {
        SimpleDictionary dictionary = new SimpleDictionary();

        if (!file.exists()) {
            System.out.println("Không tìm thấy file: " + file.getAbsolutePath());
            return dictionary;
        }

        try (Scanner scanner = new Scanner(file)) {
            int count = 0;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                if (!line.isEmpty()) {
                    String[] parts = line.split(":", 2); // Đọc dấu :
                    if (parts.length == 2) {
                        String word = parts[0].trim();
                        String meaning = parts[1].trim();
                        dictionary.addWord(word, meaning);
                        count++;
                    }
                }
            }
            System.out.println("Đã load " + count + " từ từ file.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return dictionary;
    }

    @Override
    public void save(Dictionary dictionary) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(file))) {
            for (String word : dictionary.getAllWords()) {
                String meaning = dictionary.getMeaning(word);
                writer.println(word + ":" + meaning); // Lưu theo định dạng :
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
