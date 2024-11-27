package com.eltosevenz.streams;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamsSampleOne {
    public static void main(String[] arg){

        String countriesWithCities = "India:Delhi.India:Bangalore.USA:New York.USA:New Jersey.CAN:Calgary.CAN:Boston.AUS:Sydney.AUS:Melbourne.India:Hyderabad.India:Jaipur";

        List<String>  numbers = Arrays.asList("one", "two", "three", "four", "four", "five");
        List<Integer> rNumbers = Arrays.asList(1, 9, 88, 13, 77, 42, 91, 91);

        // 1. distinct(Function - take one obj and give other) and forEach(Consumer-no out)
        System.out.println("Example 1 - distinct");
        numbers.stream().distinct().forEach(System.out::println);

        // 2. reduce(BiFunction - take two obj and give one)
        System.out.println("Example 2 - longest word");
        //here .get() used to retrieve from optional string, optional string will not throw exception for null
        String longestString = numbers.stream().reduce((n1,n2)->(n1.length()>n2.length() ? n1 : n2)).get();
        System.out.println(longestString);

        // 3. filter(Predicate - boolean)
        System.out.println("Example 3 - start with f");
        List<String> startWithF = numbers.stream().filter(n->n.startsWith("f")).collect(Collectors.toList());
        System.out.println(startWithF);

        // 4 map(Function - obj)
        System.out.println("Example 4 - make all caps");
        List<String> caps = numbers.stream().map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(caps);

        // 5 sorted(Function - obj)
        System.out.println("Example 5 - sorting");
        List<Integer> sortedNumbers = rNumbers.stream().sorted().collect(Collectors.toList());
        System.out.println(sortedNumbers);

        // 6 second largest using Comparator.reverseOrder() for desc order
        System.out.println("Example 6 - 2nd highest in an array");
        Integer secondLargest = rNumbers.stream().sorted(Comparator.reverseOrder()).skip(1).findFirst().orElse(0);
        System.out.println(secondLargest);

        // 7 get average
        System.out.println("Example 7 - find average in an array");
        double avg = rNumbers.stream().mapToDouble(Integer::doubleValue).average().orElse(0.0);
        System.out.println(avg);

        // 8 sum of all even
        System.out.println("Example 8 - sum of all even numbers");
        int sum = rNumbers.stream().filter(n->n%2==0).mapToInt(Integer::intValue).sum();
        System.out.println(sum);

        // 9 merge list
        System.out.println("Example 9 merge two list");
        List<Integer> mergedList = Stream.concat(rNumbers.stream(), rNumbers.stream()).sorted().collect(Collectors.toList());
        System.out.println(mergedList);

        // 10 Object grouping and summing - Map
        System.out.println("Example 10 object grouping and summing");
        List<Transaction> transactions = Arrays.asList(
                new Transaction("2022-01-01", 100),
                new Transaction("2022-01-01", 200),
                new Transaction("2022-01-02", 300),
                new Transaction("2022-01-02", 400),
                new Transaction("2022-01-03", 500)
        );

        Map<String, Integer> sumByDay = transactions
                .stream()
                .collect(Collectors.groupingBy(
                        Transaction::getDate,
                        Collectors.summingInt(Transaction::getAmount)
                        ));

        System.out.println(sumByDay);

        // 11 Find duplicate out
        System.out.println("Example 11 Get Duplicates from list");
        Set<Integer> duplicates = rNumbers.stream().filter(i->Collections.frequency(rNumbers,i)>1).collect(Collectors.toSet());
        System.out.println(duplicates);

        //12 Predicate Example
        System.out.println("Example 12 Predicate example");
        Predicate<Integer> predicateInt = i->Collections.frequency(rNumbers,i)>1;
        System.out.println(predicateInt.test(91));

        //13 Optional Example
        System.out.println("Example 13 Optional example");
        Optional<Integer> optionalInt = Optional.of(10); // if null null pointer exception
        System.out.println(optionalInt);
        Optional<Integer> optionalInt2 = Optional.ofNullable(null); // if null return Optional.empty no exception
        System.out.println(optionalInt2);

        //14 Map Operation - Map<String,List<String>>
        System.out.println("Example 14 Map operation -- map stream, forEach method");
        Map<String,List<String>> citiesByNation = Arrays.stream(countriesWithCities.split("\\."))
                .map(n->n.split(":"))
                // Collect into a Map, grouping by country and mapping to a list of cities
                .collect(Collectors.groupingBy(
                        arr->arr[0], // Key: country
                        Collectors.mapping(arr->arr[1],Collectors.toList()) // Value: list of cities
                ));

        citiesByNation.forEach((nation,cities)->System.out.println(nation+"-"+cities));

        //15 String lines - split string by \n as each line by line
        String line = "My name is Libin \n Hello libin ney";
        String revline = line.lines().map(a->new StringBuilder(a).reverse()).collect(Collectors.joining(" "));
        System.out.println(revline);

    }

}
