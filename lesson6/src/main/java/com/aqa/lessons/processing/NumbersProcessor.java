package com.aqa.lessons.processing;

import com.aqa.lessons.data.SumPair;
import com.aqa.lessons.enums.ModifyOptions;

import java.util.List;

public class NumbersProcessor {

    public static NumbersProcessor numbersProcessor;

    public static NumbersProcessor getInstance() {
        if (numbersProcessor == null) {
            numbersProcessor = new NumbersProcessor();
        }
        return numbersProcessor;
    }

    public void modifySumPairs(ModifyOptions modifyOptions,
                               List<SumPair> sumPairList,
                               int increaseAmount, int decreaseAmount) {
        for (SumPair sumPair : sumPairList) {
            switch (modifyOptions) {
                case FIRST_AND_SECOND_INCREASE:
                    sumPair.setFirstSum(increaseNumber(sumPair.getFirstSum(), increaseAmount));
                    sumPair.setSecondSum(increaseNumber(sumPair.getSecondSum(), increaseAmount));
                    break;
                case FIRST_INCREASE_SECOND_DECREASE:
                    sumPair.setFirstSum(increaseNumber(sumPair.getFirstSum(), increaseAmount));
                    sumPair.setSecondSum(decreaseNumber(sumPair.getSecondSum(), decreaseAmount));
                    break;
                case FIRST_DECREASE_SECOND_INCREASE:
                    sumPair.setFirstSum(decreaseNumber(sumPair.getFirstSum(), decreaseAmount));
                    sumPair.setSecondSum(increaseNumber(sumPair.getSecondSum(), increaseAmount));
                    break;
                case FIRST_DECREASE_SECOND_DECREASE:
                    sumPair.setFirstSum(decreaseNumber(sumPair.getFirstSum(), decreaseAmount));
                    sumPair.setSecondSum(decreaseNumber(sumPair.getSecondSum(), decreaseAmount));
                    break;
            }
        }
    }

    private int increaseNumber(int number, int increaseAmount) {
        return number + increaseAmount;
    }

    private int decreaseNumber(Integer number, int decreaseNumber) {
        return number - decreaseNumber;
    }

}
