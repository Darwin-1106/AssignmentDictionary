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
//download new dictionary
    Dictionary load();
//load data into existing dictionary
    void load(Dictionary dictionary);
//save dictionary to file
    void save(Dictionary dictionary);
}
