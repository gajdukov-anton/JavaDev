package com.company;

import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class WordsFinder {

    private int amountWords;
    private List<LinkedHashSet<String>> linkedHashSetList;
    private HashMap<String, Integer> hashMap;
    private FileReader fileReader = null;

    public WordsFinder(String fileName, int amountWords) throws IOException {
        this.amountWords = amountWords;
        findRepeatedWords(fileName);
    }

    private void findRepeatedWords(String fileName) throws IOException {
        try {
            linkedHashSetList = new ArrayList<>();
            fileReader = new FileReader(fileName);
            hashMap = new HashMap<String, Integer>();
            int numberofLetter;
            String wordValue = "";
            while ((numberofLetter = fileReader.read()) != -1) {
                if (Character.isLetter(numberofLetter)) {
                    wordValue += (char) numberofLetter;
                } else if (!(wordValue.equals(""))) {
                    Word word = addToHashMap(hashMap, wordValue);
                    addToHashLinkedSetList(linkedHashSetList, word);
                    wordValue = "";
                }
            }
            if (!wordValue.equals("")) {
                Word word = addToHashMap(hashMap, wordValue);
                addToHashLinkedSetList(linkedHashSetList, word);
            }
        } finally {
            if (fileReader != null) {
                fileReader.close();
            }
        }

    }


    private Word addToHashMap(HashMap<String, Integer> map, String str) {
        if (!(str.equals(""))) {
            if (map.containsKey(str)) {
                Word word = new Word(str, map.get(str) + 1);
                map.replace(word.getValue(), word.getAmount());
                return word;
            } else {
                map.put(str, 1);
                return new Word(str, 1);
            }
        }
        return null;
    }


    private void addToHashLinkedSetList(List<LinkedHashSet<String>> listOfSet, Word word) {
        if (listOfSet.isEmpty()) {
            LinkedHashSet<String> set = new LinkedHashSet<>();
            set.add(word.getValue());
            listOfSet.add(set);
        } else {
            if (listOfSet.size() >= word.getAmount()) {
                if (word.getAmount() == 1) {
                    listOfSet.get(word.getAmount() - 1).add(word.getValue());
                } else {
                    listOfSet.get(word.getAmount() - 2).remove(word.getValue());
                    listOfSet.get(word.getAmount() - 1).add(word.getValue());
                }
            } else {
                LinkedHashSet<String> set = new LinkedHashSet<>();
                set.add(word.getValue());
                listOfSet.add(set);
                listOfSet.get(word.getAmount() - 2).remove(word.getValue());
            }
        }
    }

    public void showMapWithWords() {
        for (Map.Entry entry : hashMap.entrySet()) {
            System.out.println("Key: " + entry.getKey() + " Value: "
                    + entry.getValue());
        }
    }

    public void showListWithWords() {
        int counter = 0;
        for (int i = linkedHashSetList.size() - 1; i >= 0; i--) {
            Iterator<String> iterator = linkedHashSetList.get(i).iterator();
            while (iterator.hasNext() && counter != amountWords) {
                System.out.println(iterator.next() + " " + String.valueOf(i + 1));
                counter++;
            }
            if (counter == amountWords) {
                break;
            }
        }
    }
}
