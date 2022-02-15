package com.aqa.lessons.lesson5;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Student {

    private int age;

    public int getAge() {
        System.out.println(this.age);
        return this.age;
    }

}
