package com.aqa.lessons;

import com.aqa.lessons.enums.PlanetsOfSolarSystem;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Arrays.asList(PlanetsOfSolarSystem.values()).forEach(System.out::println);
    }
}
