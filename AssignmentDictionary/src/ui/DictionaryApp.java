/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.stream.Collectors;
import model.Dictionary;
import model.SimpleDictionary;
import storage.DictionaryStorage;
import storage.IndexedFileDictionaryStorage;

public class DictionaryApp extends JFrame {
    private JTextField searchField;
    private JTextArea meaningArea;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionModel;

    private Dictionary dictionary;

    public DictionaryApp(Dictionary dictionary) {
        this.dictionary = dictionary;
        setTitle("Từ điển - Java Swing");
        setSize(600, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color lightBlue = new Color(224, 247, 250);
        getContentPane().setBackground(lightBlue);
        setLayout(new BorderLayout());

        // Gợi ý bên trái
        suggestionModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionModel);
        JScrollPane suggestionScroll = new JScrollPane(suggestionList);
        suggestionScroll.setPreferredSize(new Dimension(150, 0));
        add(suggestionScroll, BorderLayout.WEST);

        // Trung tâm: ô nhập và nghĩa
        JPanel centerPanel = new JPanel(new BorderLayout());
        centerPanel.setBackground(lightBlue);
        searchField = new JTextField();
        meaningArea = new JTextArea();
        meaningArea.setEditable(false);
        meaningArea.setLineWrap(true);
        meaningArea.setWrapStyleWord(true);

        centerPanel.add(searchField, BorderLayout.NORTH);
        centerPanel.add(new JScrollPane(meaningArea), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);

        // Sự kiện gợi ý
        searchField.getDocument().addDocumentListener(new javax.swing.event.DocumentListener() {
            public void insertUpdate(javax.swing.event.DocumentEvent e) { updateSuggestions(); }
            public void removeUpdate(javax.swing.event.DocumentEvent e) { updateSuggestions(); }
            public void changedUpdate(javax.swing.event.DocumentEvent e) {}
        });

        // Nhấn Enter tìm
        searchField.addActionListener(e -> lookupWord());

        // Chọn từ gợi ý
        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selected = suggestionList.getSelectedValue();
                if (selected != null) {
                    searchField.setText(selected);
                    lookupWord();
                }
            }
        });

        setVisible(true);
    }

    private DictionaryApp(SimpleDictionary dictionary) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public DictionaryApp(SimpleDictionary dictionary) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }


     private void updateSuggestions() {
        String input = searchField.getText().toLowerCase();
        List<String> words = dictionary.getAllWords().stream()
                .filter(w -> w.startsWith(input))
                .sorted()
                .collect(Collectors.toList());

        suggestionModel.clear();
        for (String word : words) {
            suggestionModel.addElement(word);
        }
    }

    private void lookupWord() {
        String word = searchField.getText().toLowerCase();
        String meaning = dictionary.lookup(word);
        meaningArea.setText(meaning);
    }

    public static void main(String[] args) {
        DictionaryStorage storage = new IndexedFileDictionaryStorage();
        SimpleDictionary dictionary = new SimpleDictionary(storage);
        DictionaryApp dictionaryApp = new DictionaryApp(dictionary);
    }
}