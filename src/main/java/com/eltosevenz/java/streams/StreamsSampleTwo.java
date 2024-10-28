package com.eltosevenz.java.streams;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class StreamsSampleTwo {

    public static  void main(String[] arg){
        List<String> listOfStrings = Arrays.asList("Facebook", "Twitter", "YouTube", "WhatsApp", "LinkedIn");
        List<Integer> inputNumbers = Arrays.asList(1,2,4,5,6,7,8,9,33,11,99,45,2);
        String inputStr= "AbcAbcXyzXyzzz";

        // Example 1: Partition even and odd from list
        System.out.println("Ex1: Separate Odd and even numbers from list.");
        Map<Boolean, List<Integer>> oddAeven = inputNumbers.stream().collect(Collectors.partitioningBy(n->n%2==0));
        oddAeven.forEach((n,m)->System.out.println(n+"-"+m));

        // Example 2: Frequency of each character in string
        System.out.println("Ex2: Frequency of each character in string.");
        Map<Character, Long> charCount = inputStr.chars().mapToObj(c->(char)c).collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
        charCount.forEach((n,m)->System.out.println(n+"-"+m));

        // Example 3: Frequency of each element in array
        System.out.println("Ex3: Frequency of each element in array.");
        Map<Integer,Long> arrayCount = inputNumbers.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
        arrayCount.forEach((n,m)->System.out.println(n+"-"+m));

        // Example 4: join the strings with ‘[‘ as prefix, ‘]’ as suffix and ‘,’ as delimiter
        System.out.println("Ex4: Join the list as string with prefix,suffix,delimiter.");
        String joinnedStr = listOfStrings.stream().collect(Collectors.joining(",","[","]"));
        System.out.println(joinnedStr);

        // Example 5: find min and max
        System.out.println("Ex5: Find min and max.");
        int max = inputNumbers.stream().max(Comparator.naturalOrder()).get();
        int min = inputNumbers.stream().min(Comparator.naturalOrder()).get();
        System.out.println("Min: "+min+", "+"Max: "+max);

        // Example 6: Get 3 max numbers and 3 min numbers
        System.out.println("Ex6: Get 3 max numbers and 3 min numbers");
        List<Integer> threeMins = inputNumbers.stream().distinct().sorted().limit(3).collect(Collectors.toList());
        List<Integer> threeMaxs = inputNumbers.stream().distinct().sorted(Comparator.reverseOrder()).limit(3).collect(Collectors.toList());
        System.out.println(threeMins+"-"+threeMaxs);

        // Example 7: Check if two strings are anagrams
        System.out.println("Ex7: Check if two strings are anagrams");
        String strOne = "Bare";
        String strTwo = "Bear";
        strOne = Stream.of(strOne.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        strTwo = Stream.of(strTwo.split("")).map(String::toUpperCase).sorted().collect(Collectors.joining());
        if (strOne.equals(strTwo)) {
            System.out.println("Anagrams");
        } else {
            System.out.println("Not Anagrams");
        }

        // Example 8: Sort list according to increasing order of their length
        System.out.println("Ex8: Sort list according to increasing order of their length");
        //listOfStrings.stream().sorted().forEach(System.out::println); -- will sort based on alphabet order
        listOfStrings.stream().sorted(Comparator.comparing(String::length)).forEach(System.out::println);

        // Example 9: Find common elements between two arrays
        System.out.println("Ex9: Find common elements between two arrays");
        List<Integer> list1 = Arrays.asList(71, 21, 34, 89, 56, 28);
        List<Integer> list2 = Arrays.asList(12, 56, 17, 21, 94, 34);
        List<Integer> listRes = list1.stream().filter(list2::contains).collect(Collectors.toList());
        System.out.println(listRes);

        // Example 10: Reverse each word of a string
        System.out.println("Ex10: Reverse each word of a string");
        String intro = "My name is Libin Lougine";
        String introReverse = Arrays.stream(intro.split(" ")).map(n->new StringBuilder(n).reverse()).collect(Collectors.joining(" "));
        System.out.println(introReverse);

        // Example 11: Find sum of first 10 natural numbers
        System.out.println("Ex11: Find sum of first 10 natural numbers");
        int sumVal = IntStream.range(1,11).sum(); // range then 1->10 if rangeClosed it is 1->11
        System.out.println(sumVal);

        // Example 12: Reverse array
        System.out.println("Ex12: Reverse array");
        int[] array = new int[] {5, 1, 7, 3, 9, 6};
        int[] reversedArray = IntStream.rangeClosed(1, array.length).map(i -> array[array.length - i]).toArray();
        System.out.println(Arrays.toString(reversedArray));

        // Example 13: Find the most repeated element in an array
        System.out.println("Ex13: Find the most repeated element in an array");
        List<String> listOfelems= Arrays.asList("Pen", "Eraser", "Note Book", "Pen", "Pencil", "Pen", "Note Book", "Pencil");
        Map<String, Long> elemFrequency = listOfelems.stream().collect(Collectors.groupingBy(
                Function.identity(),
                Collectors.counting()
        ));
        Map.Entry<String, Long> resultFreq = elemFrequency.entrySet().stream().max(Map.Entry.comparingByValue()).get();
        System.out.println(resultFreq.getKey()+"--"+resultFreq.getValue());

        // Example 14: Check Palindrome
        System.out.println("Ex14: Check Palindrome");
        String str = "ROTATOR";
        boolean isItPalindrome = IntStream.range(0, str.length()/2).
                noneMatch(i -> str.charAt(i) != str.charAt(str.length() - i -1));
        if (isItPalindrome)
        {
            System.out.println(str+" is a palindrome");
        }
        else
        {
            System.out.println(str+" is not a palindrome");
        }

        // Example 15: Find out those strings which start with a number
        System.out.println("Ex15: Find out those strings which start with a number");
        List<String> listOfStrings15 = Arrays.asList("One", "2wo", "3hree", "Four", "5ive", "Six");
        List<String> los15r = listOfStrings15.stream().filter(n->Character.isDigit(n.charAt(0))).collect(Collectors.toList());
        System.out.println(los15r);

        // Example 16: Extract duplicate elements from an array
        System.out.println("Ex16: Extract duplicate elements from an array");
        List<Integer> listOfIntegers16 = Arrays.asList(111, 222, 333, 111, 555, 333, 777, 222);
        Set<Integer> uniqueElements16 = new HashSet<>();
        Set<Integer> duplicateElements16 = listOfIntegers16.stream().filter(n->!uniqueElements16.add(n)).collect(Collectors.toSet());
        System.out.println(duplicateElements16);

    }
}

