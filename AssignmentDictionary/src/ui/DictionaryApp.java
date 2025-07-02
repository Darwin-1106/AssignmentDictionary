/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ui;

import model.Dictionary;
import storage.IndexedFileDictionaryStorage;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DictionaryApp extends JFrame {

    private Dictionary dictionary;
    private IndexedFileDictionaryStorage storage;

    private JTextField inputField;
    private JTextArea meaningArea;
    private JButton addButton;
    private JList<String> suggestionList;
    private DefaultListModel<String> suggestionModel;

    public DictionaryApp(Dictionary dictionary, IndexedFileDictionaryStorage storage) {
        this.dictionary = dictionary;
        this.storage = storage;

        setTitle("Từ điển Việt - Anh");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        Font font = new Font("Segoe UI", Font.PLAIN, 14);

        suggestionModel = new DefaultListModel<>();
        suggestionList = new JList<>(suggestionModel);
        suggestionList.setFont(font);
        JScrollPane scrollPane = new JScrollPane(suggestionList);
        scrollPane.setPreferredSize(new Dimension(180, 0));
        scrollPane.setBorder(BorderFactory.createTitledBorder("Gợi ý"));
        add(scrollPane, BorderLayout.WEST);
        
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(new Color(204, 229, 255));

        inputField = new JTextField();
        inputField.setFont(font);
        inputField.setBorder(BorderFactory.createTitledBorder("Nhập từ"));

        meaningArea = new JTextArea(6, 20);
        meaningArea.setFont(font);
        meaningArea.setLineWrap(true);
        meaningArea.setWrapStyleWord(true);
        JScrollPane meaningScroll = new JScrollPane(meaningArea);
        meaningScroll.setBorder(BorderFactory.createTitledBorder("Nghĩa của từ"));

        addButton = new JButton("Thêm / Cập nhật nghĩa");
        addButton.setFont(font);

        mainPanel.add(inputField, BorderLayout.NORTH);
        mainPanel.add(meaningScroll, BorderLayout.CENTER);
        mainPanel.add(addButton, BorderLayout.SOUTH);

        add(mainPanel, BorderLayout.CENTER);

        inputField.getDocument().addDocumentListener(new DocumentListener() {
            public void insertUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void removeUpdate(DocumentEvent e) {
                updateSuggestions();
            }

            public void changedUpdate(DocumentEvent e) {
                updateSuggestions();
            }
        });

        inputField.addActionListener(e -> lookupWord());

        suggestionList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                String selectedWord = suggestionList.getSelectedValue();
                if (selectedWord != null) {
                    inputField.setText(selectedWord);
                    meaningArea.setText(dictionary.getMeaning(selectedWord));
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String word = inputField.getText().trim();
                String meaning = meaningArea.getText().trim();
                if (!word.isEmpty() && !meaning.isEmpty()) {
                    dictionary.addWord(word, meaning);
                    storage.save(dictionary);
                    updateSuggestions();
                    JOptionPane.showMessageDialog(DictionaryApp.this,
                            "Đã thêm / cập nhật từ: " + word,
                            "Thành công",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(DictionaryApp.this,
                            "Vui lòng nhập đầy đủ từ và nghĩa.",
                            "Thiếu thông tin",
                            JOptionPane.WARNING_MESSAGE);
                }
            }
        });
    }

    private void updateSuggestions() {
        String prefix = inputField.getText().trim();
        suggestionModel.clear();

        if (!prefix.isEmpty()) {
            for (String word : dictionary.getSuggestions(prefix)) {
                suggestionModel.addElement(word);
            }

            for (String word : dictionary.getAllWords()) {
                if (word.equalsIgnoreCase(prefix)) {
                    meaningArea.setText(dictionary.getMeaning(word));
                    return;
                }
            }

            meaningArea.setText("");
        } else {
            meaningArea.setText("");
        }
    }

    // Hàm xử lý tra nghĩa khi nhấn Enter
    private void lookupWord() {
        String word = inputField.getText().trim();
        String meaning = dictionary.getMeaning(word);
        if (meaning != null) {
            meaningArea.setText(meaning);
        } else {
            meaningArea.setText("");
            JOptionPane.showMessageDialog(this,
                    "Không tìm thấy từ: " + word,
                    "Không có kết quả",
                    JOptionPane.WARNING_MESSAGE);
        }
    }

}
