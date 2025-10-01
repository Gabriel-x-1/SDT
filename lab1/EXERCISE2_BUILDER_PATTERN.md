# Exercise 2: Builder Pattern

## Overview
The Builder pattern constructs complex objects step by step. It allows you to create different types and representations of an object using the same construction code, providing a clean way to handle objects with many optional parameters.

## Problem Solved
- **Telescoping Constructor Problem**: Avoids having multiple constructors with different parameter combinations
- **Object Immutability**: Creates immutable objects with a flexible construction process
- **Readable Code**: Makes object creation more readable and maintainable
- **Optional Parameters**: Handles multiple optional parameters elegantly with default values

## Alternative Approaches and Their Problems

### 1. Telescoping Constructor Pattern (❌ Problematic)
```java
// Multiple constructors with increasing parameters
public Car(String brand, int year, int power, String fuel, String chassis) {...}
public Car(String brand, int year, int power, String fuel, String chassis, Sound sound) {...}
public Car(String brand, int year, int power, String fuel, String chassis, Sound sound, Navigation nav) {...}
// ... many more constructors
```
**Problems**: Hard to read, error-prone, parameter order confusion

### 2. JavaBeans Pattern (❌ Problematic)
```java
Car car = new Car();
car.setBrand("Ford");
car.setYear(2020);
// ... many setter calls
```
**Problems**: Object in inconsistent state during construction, cannot be immutable

### 3. Builder Pattern (✅ Solution)
```java
Car car = new Car.Builder("Ford", 2020, 150, "gas", "ABC123")
    .sound(Sound.MP3)
    .navigation(Navigation.GPS)
    .build();
```

## Implementation

### Core Components

#### 1. `Car` Class (Immutable Product)
```java
public class Car {
    // All fields are final (immutable)
    private final String brand;           // Required
    private final int productionYear;     // Required
    private final int enginePower;        // Required
    private final String fuelType;        // Required
    private final String chassisNumber;   // Required
    private final Sound soundSystem;      // Optional (default: RadioCD)
    private final Navigation navigation;  // Optional (default: None)
    private final Air airConditioning;   // Optional (default: MANUAL)

    // Private constructor - only Builder can create Car objects
    private Car(Builder builder) {
        this.brand = builder.brand;
        // ... copy all fields from builder
    }
}
```

#### 2. `Builder` Inner Class
```java
public static class Builder {
    // Required parameters (set in constructor)
    private final String brand;
    private final int productionYear;
    private final int enginePower;
    private final String fuelType;
    private final String chassisNumber;

    // Optional parameters with defaults
    private Sound soundSystem = Sound.RadioCD;
    private Navigation navigation = Navigation.None;
    private Air airConditioning = Air.MANUAL;

    // Constructor for required parameters
    public Builder(String brand, int year, int power, String fuel, String chassis) {
        this.brand = brand;
        // ... set required fields
    }

    // Fluent interface methods for optional parameters
    public Builder sound(Sound sound) {
        this.soundSystem = sound;
        return this;  // Return this for method chaining
    }

    public Builder navigation(Navigation nav) {
        this.navigation = nav;
        return this;
    }

    public Builder air(Air air) {
        this.airConditioning = air;
        return this;
    }

    // Build method creates the final immutable object
    public Car build() {
        return new Car(this);
    }
}
```

#### 3. Supporting Enums
```java
public enum Sound { RadioCD, MP3, Bluetooth, Premium }
public enum Navigation { None, SMALL, LARGE, GPS }
public enum Air { MANUAL, AUTO, CLIMATE }
```

## Usage Examples

### Basic Car (Required Parameters Only)
```java
Car fordTrend = new Car.Builder("Ford", 2009, 87, "diesel", "XYZ").build();
// Uses all default values for optional parameters
```

### Car with Some Optional Parameters
```java
Car fordTitanium = new Car.Builder("Ford", 2018, 125, "diesel", "WWW")
    .sound(Sound.MP3)
    .navigation(Navigation.SMALL)
    .build();
```

### Fully Loaded Car
```java
Car luxuryCar = new Car.Builder("BMW", 2023, 300, "electric", "ABC123")
    .sound(Sound.Premium)
    .navigation(Navigation.GPS)
    .air(Air.CLIMATE)
    .build();
```

## Benefits

1. **Readability**: Code clearly shows what each parameter represents
2. **Flexibility**: Any combination of optional parameters can be set
3. **Immutability**: Final object is immutable and thread-safe
4. **Validation**: Build method can validate the final object state
5. **Fluent Interface**: Method chaining creates readable code
6. **Default Values**: Sensible defaults for optional parameters

## Design Pattern Elements

- **Product**: `Car` class (the complex object being built)
- **Builder**: `Car.Builder` inner class (constructs the product)
- **Director**: Client code (calls builder methods in desired order)
- **Fluent Interface**: Method chaining for readable construction

## Key Principles

1. **Required parameters** go in the Builder constructor
2. **Optional parameters** have setter methods that return the Builder
3. **build()** method creates the final immutable object
4. **Private constructor** in the product class prevents direct instantiation

## Testing
Run the exercise with:
```bash
./run_exercises.sh 2
```

The test demonstrates:
- Basic car creation with required parameters only
- Cars with various optional parameter combinations
- Default value verification
- Immutable object properties
- Method chaining readability