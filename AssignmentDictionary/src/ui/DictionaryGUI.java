/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

/**
 *
 * @author Lenovo
 */
import model.Dictionary;

import javax.swing.*;
import java.awt.*;
public class DictionaryGUI extends JFrame{
     private final Dictionary dictionary;
    private JTextField wordField;
    private JTextArea meaningArea;

    public DictionaryGUI(Dictionary dictionary) {
        this.dictionary = dictionary;
        initUI();
    }

    private void initUI() {
        setTitle("Từ Điển Đơn Giản");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        wordField = new JTextField();
        meaningArea = new JTextArea(5, 20);
        meaningArea.setLineWrap(true);
        meaningArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(meaningArea);

        JButton searchButton = new JButton("🔍 Tra từ");
        JButton addButton = new JButton("➕ Thêm từ");

        searchButton.addActionListener(e -> onSearch());
        addButton.addActionListener(e -> onAdd());

        JPanel panel = new JPanel(new GridLayout(6, 1, 5, 5));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        panel.add(new JLabel("Từ:"));
        panel.add(wordField);
        panel.add(new JLabel("Nghĩa:"));
        panel.add(scrollPane);
        panel.add(searchButton);
        panel.add(addButton);

        add(panel);
    }

    private void onSearch() {
        String word = wordField.getText().trim();
        if (word.isEmpty()) return;
        String meaning = dictionary.lookup(word);
        meaningArea.setText(meaning);
    }

    private void onAdd() {
        String word = wordField.getText().trim();
        String meaning = meaningArea.getText().trim();
        if (word.isEmpty() || meaning.isEmpty()) return;
        dictionary.addWord(word, meaning);
        JOptionPane.showMessageDialog(this, "Đã thêm từ thành công.");
        wordField.setText("");
        meaningArea.setText("");
    }
}
