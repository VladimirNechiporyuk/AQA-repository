package com.aqa.lessons.data;

import java.util.Random;

public class DataProvider {

    private static DataProvider dataProvider;
    private final Random random;

    public static DataProvider getInstance() {
        if (dataProvider == null) {
            dataProvider = new DataProvider();
        }
        return dataProvider;
    }

    private DataProvider() {
        random = new Random();
    }

    public String[] getHardcodeStringsArray() {
        String[] strings = new String[11];
        strings[0] = "мама";
        strings[1] = "папа";
        strings[2] = "нана";
        strings[3] = "шаша";
        strings[4] = "варова";
        strings[5] = "тата";
        strings[6] = "сагыса";
        strings[7] = "зазиза";
        strings[8] = "фафа";
        strings[9] = "фофо";
        strings[10] = "ел еж ежеле";
        return strings;
    }

    public String[] getRandomWordsArray() {
        int minArrayLength = 100;
        int arrayLength = random.nextInt(900) + minArrayLength;
        String[] strings = new String[arrayLength];
        int arrayIndex = 0;
        do {
            strings[arrayIndex] = getWord();
            arrayIndex++;
        } while (arrayIndex < arrayLength);
        return strings;
    }

    private String getWord() {
        int wordLength = random.nextInt(5) + 1;
        StringBuilder wordBuilder = new StringBuilder();
        int wordIndex = 0;
        while (wordIndex < wordLength) {
            wordBuilder.append(getRandomAlphabetCharacter());
            wordIndex++;
        }
        return wordBuilder.toString();
    }

    private char getRandomAlphabetCharacter() {
        char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
        return alphabet[random.nextInt(alphabet.length)];
    }

}
