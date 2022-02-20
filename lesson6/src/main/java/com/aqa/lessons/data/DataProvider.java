package com.aqa.lessons.data;

import java.util.ArrayList;
import java.util.List;
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
        this.random = new Random();
    }

    public int provideRandomNumber(int from, int to) {
        return from + random.nextInt(to + 1 - from);
    }

    public List<Integer> provideListOfRandomNumbers(int amount, int from, int to) {
        List<Integer> numbers = new ArrayList<Integer>();
        int iteration = 0;
        while (iteration < amount) {
            numbers.add(provideRandomNumber(from, to));
            iteration++;
        }
        return numbers;
    }

    public List<SumPair> createSumPairs(List<Integer> sumList) {
        List<SumPair> sumPairList = new ArrayList<>();
        int secondSumIndex = 1;
        for (int firstSumIndex = 0; firstSumIndex < sumList.size() - 1 && secondSumIndex < sumList.size(); firstSumIndex++) {
            sumPairList.add(new SumPair(sumList.get(firstSumIndex), sumList.get(secondSumIndex)));
            secondSumIndex++;
        }
        return sumPairList;
    }

}
