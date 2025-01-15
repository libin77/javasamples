package com.eltosevenz.streams;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

class Asset{
    int assetId;
    String assetName;
    Asset(int assetId, String assetName){
        this.assetId = assetId;
        this.assetName = assetName;
    }
}

class Employee{
    int empId;
    int age;
    String name;
    List<Asset> assets;

    Employee(int empId, int age, String name, List<Asset> assets){
        this.empId = empId;
        this.age = age;
        this.name = name;
        this.assets = assets;
    }

    public int getAge() {
        return age;
    }

    public String toString() { return name+"--"+age; }
}


public class StreamSampleThree {

    public static void main(String[] arg) {

        //Ex1: Flattern List
        List<List<Integer>> listOfLists = Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(4, 5),
                Arrays.asList(6, 7, 8, 9)
        );

        // Flatten the list of lists
        List<Integer> flatList = listOfLists.stream()
                .flatMap(List::stream)
                .toList();

        System.out.println("Ex1: Flattened List: " + flatList);

        //Ex2: Flattern List inside complex objects
        List<Employee> employees = Arrays.asList(new Employee(111, 23, "abc",
                        Arrays.asList(new Asset(211, "laptop"), new Asset(212, "charger"),
                                new Asset(213, "mobile"))),
                new Employee(112, 23, "abd",
                        Arrays.asList(new Asset(217, "laptop"), new Asset(218, "charger"),
                                new Asset(219, "mobile"))));

        List<String> assetNames = employees.stream().flatMap(n->n.assets.stream().map(m->m.assetName)).toList();
        System.out.println("Ex2: Flattened List -- complex objects: "+assetNames);

        //Ex3: Flattern from Map Value
        Map<String, List<String>> map = new HashMap<>();
        map.put("Fruits", Arrays.asList("Apple", "Banana", "Cherry"));
        map.put("Vegetables", Arrays.asList("Carrot", "Pea", "Broccoli"));
        map.put("Dairy", Arrays.asList("Milk", "Cheese"));

        // Flatten all values into a single list
        List<String> flatListFromMap = map.values().stream()
                .flatMap(List::stream)
                .toList();

        System.out.println("Ex3: Flattened List from Map: " + flatListFromMap);

        //Ex4: Cartesian Product
        List<String> list1 = Arrays.asList("A", "B");
        List<String> list2 = Arrays.asList("1", "2");
        List<String> cartesianProduct = list1.stream()
                .flatMap(s1 -> list2.stream().map(s2 -> s1 + s2))
                .toList();
        System.out.println("Ex4: Cartesian Product: "+cartesianProduct);

        //Ex5: Expanding Ranges
        List<String> ranges = Arrays.asList("1-3", "5-6");
        List<Integer> expanded = ranges.stream()
                .flatMap(range -> {
                    String[] parts = range.split("-");
                    int start = Integer.parseInt(parts[0]);
                    int end = Integer.parseInt(parts[1]);
                    return IntStream.rangeClosed(start, end).boxed();
                })
                .toList();
        System.out.println("Ex5: Expanding Ranges: "+expanded);

        //Ex6: Given a list of employees with their age, group them by age and collect all employees into a single list.
        List<Employee> empNames = employees.stream().collect(Collectors.groupingBy(Employee::getAge)).values().stream().flatMap(
                Collection::stream)
        .toList();
        System.out.println("Ex6: Grouping and Flattening: "+empNames);
    }
}
