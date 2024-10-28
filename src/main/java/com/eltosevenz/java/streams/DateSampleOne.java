package com.eltosevenz.java.streams;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class DateSampleOne {
    public static void main(String[] ar){
        // Example 1 Calculate age from DOB
        System.out.println("Ex1 - Calculate age from DOB");
        LocalDate birthDay = LocalDate.of(1989, 04, 18);
        LocalDate today = LocalDate.now();
        System.out.println(ChronoUnit.YEARS.between(birthDay, today));
    }
}
