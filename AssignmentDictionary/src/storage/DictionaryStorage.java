/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package storage;

/**
 *
 * @author Lenovo
 */
import java.util.Map;
public interface DictionaryStorage {
    void load(Map<String, String> data);
    void save(Map<String, String> data);
}
