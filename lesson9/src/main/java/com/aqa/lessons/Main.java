package com.aqa.lessons;

import com.aqa.lessons.data.DataProvider;

import java.util.*;

public class Main {

    private static final DataProvider DATA_PROVIDER = DataProvider.getInstance();
    private static final int amountOfWordsWithEvenSymbols = 2;

    public static void main(String[] args) {
        System.out.println(String.format("Hardcode words: %s", getUniqueCharArrayFromHardcodeArray(DATA_PROVIDER.getHardcodeStringsArray())));
        System.out.println(String.format("Random words: %s", getUniqueCharArrayFrom(DATA_PROVIDER.getRandomWordsArray())));
    }

    private static Set<Character> getUniqueCharArrayFromHardcodeArray(String[] stringsArray) {
        return getUniqueCharArray(stringsArray);
    }

    private static Set<Character> getUniqueCharArrayFrom(String[] stringsArray) {
        return getUniqueCharArray(stringsArray);
    }

    private static Set<Character> getUniqueCharArray(String[] stringsArray) {
        String[] wordsWithEvenAmountOfSymbols = new String[amountOfWordsWithEvenSymbols];
        int indexOfWordsWithEvenSymbols = 0;
        Set<Character> allUniqueSymbols = new HashSet<>();
        for (String word : stringsArray) {
            Map<Character, Integer> charactersWithAmounts = new HashMap<>();
            for (int symbolIndex = 0; symbolIndex < word.length(); symbolIndex++) {
                Character symbol = word.charAt(symbolIndex);
                if (charactersWithAmounts.containsKey(symbol)) {
                    Integer amountOfSymbols = charactersWithAmounts.get(symbol);
                    charactersWithAmounts.put(symbol, ++amountOfSymbols);
                } else {
                    charactersWithAmounts.put(symbol, 1);
                }
            }
            boolean isEverySymbolHasEvenAmount = true;
            for (Map.Entry entry : charactersWithAmounts.entrySet()) {
                int amountOfSymbols = (int) entry.getValue();
                if (amountOfSymbols % 2 != 0) {
                    isEverySymbolHasEvenAmount = false;
                    break;
                }
            }
            if (isEverySymbolHasEvenAmount) {
                wordsWithEvenAmountOfSymbols[indexOfWordsWithEvenSymbols] = word;
                indexOfWordsWithEvenSymbols++;
            }
            if (indexOfWordsWithEvenSymbols == amountOfWordsWithEvenSymbols) {
                break;
            }
        }
        for (String word : wordsWithEvenAmountOfSymbols) {
            System.out.println(String.format("Word: %s", word));
            for (int symbolIndex = 0; symbolIndex < word.length(); symbolIndex++) {
                allUniqueSymbols.add(word.charAt(symbolIndex));
            }
        }
        return allUniqueSymbols;
    }
}
