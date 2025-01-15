package com.eltosevenz.streams;

import java.util.*;
import java.util.stream.Collectors;

class Person {
    String firstName;
    String lastName;
    int age;

    public Person(String firstName, String lastName, int age) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public int getAge() { return age; }

    @Override
    public String toString() {
        return firstName + " " + (lastName == null ? "null" : lastName) + " (" + age + ")";
    }
}

public class StreamSampleFour {

    public static List<Person> sortByMultipleFieldsAscending(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getLastName)
                        .thenComparing(Person::getFirstName))
                .collect(Collectors.toList());
    }

    public static List<Person> sortByMixedOrder(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getLastName)
                        .thenComparing(Comparator.comparing(Person::getAge).reversed()))
                .collect(Collectors.toList());
    }

    public static List<Person> sortWithNullHandling(List<Person> people) {
        return people.stream()
                .sorted(Comparator.comparing(Person::getLastName, Comparator.nullsFirst(String::compareTo))
                        .thenComparing(Person::getAge))
                .collect(Collectors.toList());
    }

    public static List<Person> sortByDerivedFields(List<Person> people) {
        return people.stream()
                .filter(Objects::nonNull) // Skip null person objects
                .sorted(Comparator.comparingInt(Person::getAge)
                        .thenComparing(Person::getFirstName, Comparator.nullsFirst(String::compareTo)))
                .collect(Collectors.toList());
    }


    public static List<Person> sortByCustomLogic(List<Person> people) {
        return people.stream()
                .sorted(Comparator.<Person, Boolean>comparing(person -> person.getLastName() != null && person.getLastName().startsWith("D")).reversed()
                        .thenComparing(Person::getAge))
                .collect(Collectors.toList());
    }

    public static List<Person> getTopNYoungest(List<Person> people, int n) {
        return people.stream()
                .sorted(Comparator.comparingInt(Person::getAge))
                .limit(n)
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        List<Person> people = Arrays.asList(
                new Person("John", "Doe", 25),
                new Person("Jane", "Doe", 22),
                new Person("John", "Smith", 30),
                new Person("Emily", "null", 28),
                new Person("null", "Brown", 40),
                new Person("Alice", "Davis", 19)
        );

        System.out.println("1. Sort by LastName, then FirstName (Ascending):");
        sortByMultipleFieldsAscending(people).forEach(System.out::println);

        System.out.println("\n2. Sort by LastName (Ascending), then Age (Descending):");
        sortByMixedOrder(people).forEach(System.out::println);

        System.out.println("\n3. Sort with Null Handling (LastName, then Age):");
        sortWithNullHandling(people).forEach(System.out::println);

        System.out.println("\n4. Sort by Derived Field (LastName Length, then FirstName):");
        sortByDerivedFields(people).forEach(System.out::println);

        System.out.println("\n5. Sort by Custom Logic (LastName starts with 'D', then Age):");
        sortByCustomLogic(people).forEach(System.out::println);

        System.out.println("\n6. Get Top 2 Youngest People:");
        getTopNYoungest(people, 2).forEach(System.out::println);
    }
}

