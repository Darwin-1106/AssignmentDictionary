/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
import model.Dictionary;
import storage.IndexedFileDictionaryStorage;
import ui.DictionaryApp;

import javax.swing.*;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
         SwingUtilities.invokeLater(() -> {
            String filePath = "dictionary.dat";
            IndexedFileDictionaryStorage storage = new IndexedFileDictionaryStorage(filePath);
            Dictionary dictionary = storage.load();

            DictionaryApp app = new DictionaryApp(dictionary, storage);
            app.setVisible(true);
        });
    }
}
