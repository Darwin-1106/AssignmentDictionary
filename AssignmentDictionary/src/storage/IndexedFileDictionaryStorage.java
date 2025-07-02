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
import java.util.*;

public class IndexedFileDictionaryStorage implements DictionaryStorage {

    private final String dataFile = "dictionary.dat";
    private final String indexFile = "index.idx";
    private final Map<String, Long> indexMap = new HashMap<>();

    public IndexedFileDictionaryStorage() {
        loadIndex();
    }

    @Override
    public void load(Map<String, String> data) {
        try (RandomAccessFile raf = new RandomAccessFile(dataFile, "r")) {
            for (Map.Entry<String, Long> entry : indexMap.entrySet()) {
                raf.seek(entry.getValue());
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                int b;
                while ((b = raf.read()) != -1 && b != '\n') {
                    baos.write(b);
                }
                String line = baos.toString("UTF-8");
                System.out.println("Đang đọc từ offset " + entry.getValue() + ": " + line);
                if (line != null && line.contains(":")) {
                    String[] parts = line.split(":", 2);
                    data.put(parts[0].toLowerCase(), parts[1]);
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi khi đọc dictionary.dat: " + e.getMessage());
        }
    }

    @Override
    public void save(Map<String, String> data) {
        try (RandomAccessFile raf = new RandomAccessFile(dataFile, "rw"); BufferedWriter indexWriter = new BufferedWriter(new FileWriter(indexFile))) {
            raf.seek(raf.length()); // ghi vào cuối file
            for (Map.Entry<String, String> entry : data.entrySet()) {
                if (!indexMap.containsKey(entry.getKey())) {
                    long pos = raf.getFilePointer();
                    String line = entry.getKey() + ":" + entry.getValue() + "\n";
                    raf.writeBytes(line);
                    indexMap.put(entry.getKey(), pos);
                }
            }

            for (Map.Entry<String, Long> idx : indexMap.entrySet()) {
                indexWriter.write(idx.getKey() + ":" + idx.getValue());
                indexWriter.newLine();
            }
        } catch (IOException e) {
            System.out.println("Lỗi ghi file: " + e.getMessage());
        }
    }

    private void loadIndex() {
        File file = new File(indexFile);
        if (!file.exists()) {
            buildIndexFromDataFile(); // THÊM DÒNG NÀY
            return;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    indexMap.put(parts[0].toLowerCase(), Long.parseLong(parts[1]));
                }
            }
        } catch (IOException e) {
            System.out.println("Lỗi đọc index: " + e.getMessage());
        }
    }
    
    private void buildIndexFromDataFile() {
    try (RandomAccessFile raf = new RandomAccessFile(dataFile, "r")) {
        long pos;
        String line;
        while ((pos = raf.getFilePointer()) < raf.length()) {
            line = raf.readLine();
            if (line != null && line.contains(":")) {
                String[] parts = line.split(":", 2);
                indexMap.put(parts[0].toLowerCase(), pos);
            }
        }
    } catch (IOException e) {
        System.out.println("Lỗi tạo index từ file data: " + e.getMessage());
    }
}


}
