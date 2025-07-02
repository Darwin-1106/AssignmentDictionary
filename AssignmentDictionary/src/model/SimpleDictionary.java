/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author Lenovo
 */
import java.util.*;
import storage.DictionaryStorage;

public class SimpleDictionary extends Dictionary_1{
     public SimpleDictionary(DictionaryStorage storage) {
        super(storage);
    }

    @Override
    public List<String> getAllWords() {
        return new ArrayList<>(data.keySet());
    }

    @Override
    public String lookup(String word) {
        return data.getOrDefault(word.toLowerCase(), "Không tìm thấy từ.");
    }

    // Có thể thêm phương thức thêm từ, xóa, sửa tại đây
}
}
