# Comparable vs Comparator in Java

## Overview

When working with sorting in Java, you often encounter two interfaces: **`Comparable`** and **`Comparator`**. Both are used to compare objects, but they serve different purposes.

---

## Comparable

The `Comparable` interface is used to define the **natural ordering** of objects. A class implements `Comparable` to specify how its instances should be compared.

### Key Points
- Part of the `java.lang` package.
- Requires implementing the `compareTo()` method.
- Defines a single sorting sequence.

### Usage
- Used when the class itself should define its natural sorting order.
- Often used in scenarios where objects are naturally ordered, like Strings or Numbers.

### Example
```java
class Student implements Comparable<Student> {
    int id;
    String name;

    public Student(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public int compareTo(Student other) {
        return this.name.compareTo(other.name); // Natural order by name
    }
}
```

Sorting a list using `Comparable`:
```java
List<Student> students = Arrays.asList(
    new Student(2, "Alice"),
    new Student(1, "Bob")
);

students.sort(null); // Uses Comparable's compareTo() method
```

---

## Comparator

The `Comparator` interface is used to define **custom ordering** for objects. Unlike `Comparable`, this is external to the class being compared.

### Key Points
- Part of the `java.util` package.
- Requires implementing the `compare()` method.
- Can define multiple sorting sequences.

### Usage
- Used when you want to sort objects in a way that is not their natural order.
- Allows for more flexible and reusable sorting logic.

### Example
```java
List<Student> students = Arrays.asList(
    new Student(2, "Alice"),
    new Student(1, "Bob")
);

// Sort by ID using Comparator
students.sort(Comparator.comparingInt(student -> student.id));
```

---

## Key Differences

| Aspect               | Comparable                      | Comparator                       |
|----------------------|----------------------------------|-----------------------------------|
| **Package**          | `java.lang`                    | `java.util`                      |
| **Method**           | `compareTo(Object o)`          | `compare(Object o1, Object o2)`  |
| **Sorting Logic**    | Defined within the class itself | Defined externally               |
| **Number of Orders** | Single natural order            | Multiple custom orders possible  |


## Key Differences in Java 8 Streams
### With Comparable
The compareTo() method of the class is invoked implicitly when sorting via sorted() without an explicit Comparator.
Example:
```java
students.stream()
.sorted() // Uses Comparable's compareTo()
.forEach(s -> System.out.println(s.name));
```

### With Comparator
Provides more flexibility as you can specify the sorting criteria at runtime.
Commonly used with Stream.sorted() for custom sorting.
Example:
```java
students.stream()
.sorted(Comparator.comparingInt(s -> s.id)) // Custom sorting by ID
.forEach(s -> System.out.println(s.name));
```
---

## When to Use
- **Use `Comparable`** when a class has a natural ordering (e.g., Strings sorted alphabetically, integers numerically).
- **Use `Comparator`** when you need custom or multiple sorting orders, especially for flexibility or reusability.
