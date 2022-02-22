package com.aqa.lessons.validators;

import com.aqa.lessons.data.SumPair;

import java.util.List;

public class SumValidator {

    public static SumValidator sumValidator;

    public static SumValidator getInstance() {
        if (sumValidator == null) {
            sumValidator = new SumValidator();
        }
        return sumValidator;
    }

    public void validateListSumsIsFirstSumHighers(List<SumPair> sumPairList) {
        for (SumPair sumPair : sumPairList) {
            validateSumsIsFirstSumHighers(sumPair);
        }
    }

    private void validateSumsIsFirstSumHighers(SumPair sumPair) {
        if (sumPair.getFirstSum() > sumPair.getSecondSum()) {
            System.out.println(String.format("First sum: %d. Second sum: %d, Is first sum highest: %b",
                    sumPair.getFirstSum(), sumPair.getSecondSum(), true));
        } else {
            System.out.println(String.format("First sum: %d. Second sum: %d, Is first sum highest: %b",
                    sumPair.getFirstSum(), sumPair.getSecondSum(), false));
        }
    }

}
