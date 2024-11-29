package com.eltosevenz.design.Builder;

import java.util.List;
import java.util.Map;

/*
True Immutability:
Prevents any modifications to the state of the object after construction.
Ensures thread safety without requiring synchronization.

Encapsulation:
Defensive copying prevents external modifications through references.

Flexibility with Builder:
Easy to construct objects with optional or complex attributes.

Safe Collections:
Wrapping collections ensures they cannot be modified.

This pattern guarantees that the ImmutablePerson object is completely immutable and safe to use in concurrent or multi-threaded environments.
 */
public class Main {
    public static void main(String[] args) {
        Address address = new Address("Boston", "MA");
        List<String> hobbies = List.of("Reading", "Cycling");
        Map<String, String> direct = Map.of("Home", "123-456-7890", "Work", "987-654-3210");

        ImmutablePerson person = new ImmutablePerson.Builder()
                .setName("Libin Lougine")
                .setAge(35)
                .setHobbies(hobbies)
                .setAddress(address)
                .setDirect(direct)
                .build();

        System.out.println(person);

        // Attempt to modify hobbies
        // person.getHobbies().add("Swimming"); // Throws UnsupportedOperationException

        // Attempt to modify direct
        // person.getDirect().put("Mobile", "111-222-3333"); // Throws UnsupportedOperationException
    }
}

