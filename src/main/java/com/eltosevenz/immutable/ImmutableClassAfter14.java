package com.eltosevenz.immutable;

import java.util.Collections;
import java.util.List;

public record ImmutableClassAfter14(String name, int age, List<String> hobbies) {

    // Custom constructor to create a defensive copy of the hobbies list
    public ImmutableClassAfter14 {
        if (hobbies == null) {
            throw new IllegalArgumentException("Hobbies cannot be null");
        }
        hobbies = List.copyOf(hobbies); // Creates an unmodifiable copy of the list
    }

    // Additional methods, if needed, can be added here
}

/*
record Declaration:
By declaring ImmutableClass as a record, you automatically get:
An implicit constructor with all fields.
Getter methods for each field (name(), age(), hobbies()).
Proper toString, equals, and hashCode implementations.

Defensive Copy:
A record allows you to customize the canonical constructor.
Here, List.copyOf(hobbies) ensures that the hobbies list is unmodifiable and prevents external modification.

Null Checks:
Added a null check to ensure that hobbies is not null. This enforces immutability and avoids runtime errors.

No Explicit Methods:
record automatically provides the accessor methods (name(), age(), and hobbies()), so you don't need to manually write getters.
 */

