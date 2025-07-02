/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */

/**
 *
 * @author Lenovo
 */
import model.Dictionary;
import model.SimpleDictionary;
import storage.DictionaryStorage;
import storage.IndexedFileDictionaryStorage;
import ui.DictionaryApp;

public class main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
   javax.swing.SwingUtilities.invokeLater(() -> {
            DictionaryStorage storage = new IndexedFileDictionaryStorage(); // Sử dụng chỉ mục
            SimpleDictionary dictionary = new SimpleDictionary(storage);
            DictionaryApp app = new DictionaryApp(dictionary);
            gui.setVisible(true);
        });
    }
}
