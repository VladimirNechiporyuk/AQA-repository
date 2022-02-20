package com.aqa.lessons;

import com.aqa.lessons.data.DataProvider;
import com.aqa.lessons.processing.SumProcessor;

import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final DataProvider dataProvider = DataProvider.getInstance();
    private static final SumProcessor sumProcessor = SumProcessor.getInstance();

    public static void main(String[] args) {
        System.out.println("method: variantWhereSumOnlyPairs");
        variantWhereSumOnlyPairs(4);

        System.out.println("\nmethod: variantWhereSumEachToEachNumber");
        sumProcessor.variantWhereSumEachToEachNumber(4);
    }

    private static void variantWhereSumOnlyPairs(int amountOfNumbers) {
        if (amountOfNumbers % 2 != 0) {
            System.out.println(String.format("Incorrect provided amount of numbers. Numbers should de on 2. " +
                    "\nProvided amount of number is: %d", amountOfNumbers));
            return;
        }

//        task 1
        List<Integer> numbers = dataProvider.provideListOfRandomNumbers(amountOfNumbers, 1, 5);
        System.out.println(String.format("Numbers: %s", numbers));

//        task 2
        List<Integer> sumList = createSumList(numbers);

//        task 3
        compareSums(sumList);

//        tasks 4, 5
        modifySums(sumList);

//        task 6
        compareSums(sumList);

//        task 7
        isAnySumDividesOnTwo(sumList);
    }

    private static void modifySums(List<Integer> sumList) {
        int firstSumIndex = 0;
        int secondSumIndex = 1;
        while (firstSumIndex < sumList.size() - 1 && secondSumIndex < sumList.size()) {
            sumList.set(firstSumIndex, sumList.get(firstSumIndex) + 1);
            sumList.set(secondSumIndex, sumList.get(secondSumIndex) - 2);
            firstSumIndex += 2;
            secondSumIndex += 2;
        }
    }

    private static List<Integer> createSumList(List<Integer> numbers) {
        List<Integer> sumList = new ArrayList<>();
        int firstNumberIndex = 0;
        int secondNumberIndex = 1;
        while (firstNumberIndex < numbers.size() - 1 && secondNumberIndex < numbers.size()) {
            int firstNumber = numbers.get(firstNumberIndex);
            int secondNumber = numbers.get(secondNumberIndex);
            sumList.add(firstNumber + secondNumber);
            firstNumberIndex += 2;
            secondNumberIndex += 2;
        }
        return sumList;
    }

    private static void compareSums(List<Integer> sumList) {
        int firstSumIndex = 0;
        int secondSumIndex = 1;

        while (firstSumIndex < sumList.size() - 1 && secondSumIndex < sumList.size()) {
            int firstSum = sumList.get(firstSumIndex);
            int secondSum = sumList.get(secondSumIndex);
            System.out.println(String.format("Sums: %d and %d. Is first sum bigger than second: %b",
                    firstSum, secondSum, firstSum > secondSum));
            firstSumIndex += 2;
            secondSumIndex += 2;
        }
    }

    private static void isAnySumDividesOnTwo(List<Integer> sumList) {
        for (int sum : sumList) {
            if (sum % 2 == 0) {
                System.out.println(String.format("The sum %d divides on 2: %b", sum, true));
                break;
            }
        }
    }

}
