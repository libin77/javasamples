package com.eltosevenz.design.Builder;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/*
Immutable Class with Builder Pattern

final Fields:
All fields in ImmutablePerson and Address are declared as final to ensure their values cannot be reassigned.

Immutable Collections:
hobbies is wrapped with Collections.unmodifiableList and a defensive copy is made using List.copyOf().
direct is wrapped with Collections.unmodifiableMap and copied with Map.copyOf().

Defensive Copies:
For the address field, the Address object is copied using a copy constructor.

Builder Pattern:
The Builder class provides a flexible way to construct the ImmutablePerson object,
especially for handling optional or complex attributes.
 */
public final class ImmutablePerson {
    private final String name;
    private final int age;
    private final List<String> hobbies;
    private final Address address;
    private final Map<String, String> direct;

    // Private constructor
    private ImmutablePerson(Builder builder) {
        this.name = builder.name;
        this.age = builder.age;
        this.hobbies = Collections.unmodifiableList(builder.hobbies != null ? List.copyOf(builder.hobbies) : Collections.emptyList());
        this.address = builder.address != null ? new Address(builder.address) : null; // Defensive copy
        this.direct = builder.direct != null ? Collections.unmodifiableMap(Map.copyOf(builder.direct)) : Collections.emptyMap();
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public List<String> getHobbies() {
        return hobbies;
    }

    public Address getAddress() {
        return address; // Already immutable
    }

    public Map<String, String> getDirect() {
        return direct;
    }

    @Override
    public String toString() {
        return "ImmutablePerson{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", hobbies=" + hobbies +
                ", address=" + address +
                ", direct=" + direct +
                '}';
    }

    // Builder class for step-by-step construction
    public static class Builder {
        private String name;
        private int age;
        private List<String> hobbies;
        private Address address;
        private Map<String, String> direct;

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setAge(int age) {
            this.age = age;
            return this;
        }

        public Builder setHobbies(List<String> hobbies) {
            this.hobbies = hobbies;
            return this;
        }

        public Builder setAddress(Address address) {
            this.address = address;
            return this;
        }

        public Builder setDirect(Map<String, String> direct) {
            this.direct = direct;
            return this;
        }

        public ImmutablePerson build() {
            return new ImmutablePerson(this);
        }
    }
}

final class Address {
    private final String city;
    private final String state;

    // Constructor for Address
    public Address(String city, String state) {
        this.city = city;
        this.state = state;
    }

    // Copy constructor
    public Address(Address address) {
        this.city = address.city;
        this.state = address.state;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @Override
    public String toString() {
        return "Address{" +
                "city='" + city + '\'' +
                ", state='" + state + '\'' +
                '}';
    }
}