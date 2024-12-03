package com.eltosevenz.design.creational.Prototype;

import java.util.HashMap;
import java.util.Map;

/*
The Prototype Design Pattern is a creational design pattern used in software development to create duplicate objects
while keeping the performance high. It is particularly useful when the object creation process is costly or complex.

Key Concepts
Prototype: A prototype object is used as a template to create new objects by copying this prototype.

Shallow Copy vs. Deep Copy:
Shallow Copy: Duplicates the object structure but not the objects referenced within it.
Deep Copy: Duplicates the object structure along with all objects it references.

When to Use
When creating a new object is resource-intensive (e.g., due to database calls or complex computations).
When creating a new object requires a lot of manual setup or configuration.

 */
// Prototype interface
interface Prototype {
    Prototype clone();
}

// Concrete class implementing the Prototype interface
class Shape implements Prototype {
    private String type;

    public Shape(String type) {
        this.type = type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    @Override
    public Shape clone() {
        return new Shape(this.type);
    }

    @Override
    public String toString() {
        return "Shape{" + "type='" + type + '\'' + '}';
    }
}

// Prototype Registry
class ShapeRegistry {
    private Map<String, Shape> shapes = new HashMap<>();

    public void addShape(String key, Shape shape) {
        shapes.put(key, shape);
    }

    public Shape getShape(String key) {
        Shape shape = shapes.get(key);
        return shape.clone(); // Return a clone of the stored shape
    }
}

// Usage Example
public class PrototypePatternDemo {
    public static void main(String[] args) {
        // Create a shape registry
        ShapeRegistry registry = new ShapeRegistry();

        // Add shapes to the registry
        registry.addShape("circle", new Shape("Circle"));
        registry.addShape("square", new Shape("Square"));

        // Retrieve and clone shapes from the registry
        Shape clonedShape1 = registry.getShape("circle");
        Shape clonedShape2 = registry.getShape("square");

        // Modify a cloned shape
        clonedShape1.setType("Ellipse");

        System.out.println("Original circle: " + registry.getShape("circle"));
        System.out.println("Cloned and modified shape: " + clonedShape1);
        System.out.println("Cloned shape 2: " + clonedShape2);
    }
}
