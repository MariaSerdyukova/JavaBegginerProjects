/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.PrintWriter;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Scanner;

/**
 * @author Maria Serdyukova
 */
public class SaveableDictionary {

    private HashMap<String, String> dictionary;
    private String fileName;

    public SaveableDictionary() {
        this.dictionary = new HashMap<>();
    }

    public SaveableDictionary(String file) {
        this();
        this.fileName = file;
    }

    public boolean load() {
        try {
            Scanner scanner = new Scanner(Paths.get(fileName));
            while (scanner.hasNextLine()) {
                String row = scanner.nextLine();
                String[] parts = row.split(":");

                dictionary.putIfAbsent(parts[0], parts[1]);
            }
        } catch (Exception e) {
            return false;
        }

        return true;
    }

    public void add(String words, String translation) {
        this.dictionary.putIfAbsent(words, translation);
    }

    public String translate(String word) {
        if (dictionary.containsKey(word)) {
            return dictionary.get(word);
        } else if (dictionary.containsValue(word)) {
            for (String key : dictionary.keySet()) {
                if (dictionary.get(key).equals(word)) {
                    return key;
                }
            }
        }
        return null;
    }

    public void delete(String word) {
        if (dictionary.containsKey(word)) {
            dictionary.remove(word);
        } else if (dictionary.containsValue(word)) {
            for (String key : dictionary.keySet()) {
                if (dictionary.get(key).equals(word)) {
                    dictionary.remove(key);
                    break;
                }
            }
        }
    }

    public boolean save() {

        try {
            PrintWriter writer = new PrintWriter(fileName);
            //String textToSave;
            for (String word : dictionary.keySet()) {

                writer.println(word + ":" + dictionary.get(word));
            }

            writer.close();
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
