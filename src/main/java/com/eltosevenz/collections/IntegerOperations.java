package com.eltosevenz.collections;


import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class IntegerOperations {


    public static int reverseInt(int input){
        List<Integer> positions = new ArrayList<>();
        int reversed = 0;


        while(input >= 1){
            int digit = input%10;
            reversed = reversed * 10 + digit;
            input/=10;
        }


        return reversed;
    }


    public static List<Integer> getDigitPositions(int target, int input){
        List<Integer> positions = new ArrayList<>();
        int length = 0;
        int position = 0;


        while(input >= 1){
            length++;
            int rem  = input%10;
            if (target == rem){
                positions.add(position);
            }
            input = input/10;
            position++;
        }


        int finalLength = length;
        return positions.stream().map(n-> finalLength-n-1).collect(Collectors.toList());
    }


    public static void main(String[] args) {
        System.out.println("Reversed Integer: "+IntegerOperations.reverseInt(874532));
        System.out.println("Positions of 2 "+IntegerOperations.getDigitPositions(2,654322872));
    }
}
