package com.eltosevenz.immutable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/*
To create an immutable class in Java, follow these guidelines:
1 Declare the class as final: This prevents subclassing, which could modify the behavior of the class.
2 Make all fields private and final: private: Prevents direct access, final: Ensures the field reference cannot be changed after initialization.
3 Do not provide setter methods: This ensures that the values cannot be modified.
4 Initialize fields via a constructor: Assign all fields during object creation.
5 Perform deep copies for mutable fields: If the class contains mutable objects, ensure their copies are returned instead of references.
6 Avoid this reference escape during construction: Ensure that no references to the 'this' object leak during object creation.
 */
public final class ImmutableClass {
    private final String name;
    private final int age;
    private final List<String> hobbies;

    public ImmutableClass(String name, int age, List<String> hobbies) {
        this.name = name;
        this.age = age;
        this.hobbies = new ArrayList<>(hobbies); // Defensive copy
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return Collections.unmodifiableList(hobbies); // Unmodifiable view
    }
}

/*
Advanced Concepts Related to Immutable Classes

Benefits of Immutability:
Thread safety: Immutable objects are inherently thread-safe as their state cannot change.
Caching and performance: Can be reused without copying, avoiding synchronization overhead.
Safe sharing: Multiple references to the same object won't cause inconsistencies.

String as an Immutable Class:
Java's String class is a prime example of immutability. Once created, the String object cannot be altered. For any modification, a new String is created.

Immutable Wrappers:
Collections.unmodifiableList, unmodifiableMap, etc., are utility methods to create immutable views of collections.

Immutability in Functional Programming:
Many functional programming paradigms (e.g., streams, lambda expressions) favor immutability for predictable behavior.

Record Classes (Java 14+):
Java introduced record types to simplify immutable class creation.
Example:
public record ImmutableRecord(String name, int age) {}

Copy-on-Write and Defensive Copies:
Immutable classes often use copy-on-write strategies for mutable components.

Immutability vs. Final Fields:
Declaring fields as final guarantees the reference cannot be changed, but the object it points to can still be mutable.

Serialization of Immutable Objects:
Ensure proper handling of immutable classes during serialization by marking fields as transient if necessary or using custom readObject
and writeObject methods.

Builder Pattern:
For creating complex immutable objects, the Builder pattern is often used
 */