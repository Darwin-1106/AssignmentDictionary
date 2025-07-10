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

public interface DictionaryStorage {
    Dictionary load();

    void load(Dictionary dictionary);

    void save(Dictionary dictionary);
}
