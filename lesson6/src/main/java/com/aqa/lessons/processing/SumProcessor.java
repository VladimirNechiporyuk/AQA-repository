package com.aqa.lessons.processing;

import com.aqa.lessons.validators.SumValidator;
import com.aqa.lessons.data.DataProvider;
import com.aqa.lessons.data.SumPair;

import java.util.ArrayList;
import java.util.List;

import static com.aqa.lessons.enums.ModifyOptions.FIRST_INCREASE_SECOND_DECREASE;

public class SumProcessor {

    private static SumProcessor sumProcessor;
    private final DataProvider dataProvider = DataProvider.getInstance();
    private final SumValidator sumValidator = SumValidator.getInstance();
    private final NumbersProcessor numbersProcessor = NumbersProcessor.getInstance();


    public static SumProcessor getInstance() {
        if (sumProcessor == null) {
            sumProcessor = new SumProcessor();
        }
        return sumProcessor;
    }

    public void variantWhereSumEachToEachNumber(int amountOfNumbers) {
//        task 1
        List<Integer> numbers = dataProvider.provideListOfRandomNumbers(amountOfNumbers, 2, 5);
        System.out.println(String.format("Numbers: %s", numbers));

//        task 2
        List<Integer> sumList = sumProcessor.sumEachNumberProcess(numbers);
        System.out.println(String.format("All sums each to each: %s", sumList));

        List<SumPair> sumPairList = dataProvider.createSumPairs(sumList);

//        task 3
        sumValidator.validateListSumsIsFirstSumHighers(sumPairList);

//        tasks 4, 5
        numbersProcessor.modifySumPairs(FIRST_INCREASE_SECOND_DECREASE, sumPairList, 1, 2);

//        task 6
        sumValidator.validateListSumsIsFirstSumHighers(sumPairList);

//        task 7
        sumProcessor.isAnySumDividessOnTwo(sumPairList);
    }

    private List<Integer> sumEachNumberProcess(List<Integer> numbers) {
        List<Integer> sumList = new ArrayList<>();
        for (int firstNumberIndex = 0; firstNumberIndex < numbers.size(); firstNumberIndex++) {
            for (int secondNumberIndex = firstNumberIndex; secondNumberIndex < numbers.size(); secondNumberIndex++)
                if (firstNumberIndex != secondNumberIndex) {
                    int firstNumber = numbers.get(firstNumberIndex);
                    int secondNumber = numbers.get(secondNumberIndex);
                    sumList.add(sumNumbers(firstNumber, secondNumber));
                }
        }
        return sumList;
    }

    private void isAnySumDividessOnTwo(List<SumPair> sumPairList) {
        boolean result = false;
        for (SumPair sumPair : sumPairList) {
            if (sumPair.getFirstSum() % 2 == 0 || sumPair.getSecondSum() % 2 == 0) {
                System.out.println(String.format("Sum %d divides on 2: %b", sumPair.getFirstSum(), true));
                result = true;
                break;
            }
        }

        if (result) {
            return;
        }

        int lastSum = sumPairList.get(sumPairList.size() - 1).getSecondSum();
        if (lastSum % 2 == 0) {
            System.out.println(String.format("Sum %d divides on 2: %b", lastSum, true));
        } else {
            System.out.println(String.format("Is any sum de on 2: %b", false));
        }
    }

    private int sumNumbers(int firstNumber, int secondNumber) {
        return firstNumber + secondNumber;
    }

}
