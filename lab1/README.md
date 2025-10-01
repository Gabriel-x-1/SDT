# Design Patterns Lab 1

**Software Design Techniques - Laboratory Assignment 1**

A comprehensive implementation of three fundamental design patterns: Factory Method, Builder, and Decorator patterns in Java.

## 📋 Overview

This laboratory demonstrates practical implementations of three essential creational and structural design patterns:

1. **Factory Method Pattern** - Interface for creating objects without specifying exact classes
2. **Builder Pattern** - Step-by-step construction of complex objects with optional parameters
3. **Decorator Pattern** - Dynamic addition of behaviors to objects at runtime

## 🗂️ Project Structure

```
lab1/
├── src/                           # Source code
│   ├── MyList.java               # Factory Method interface
│   ├── MyArrayList.java          # Array-based implementation
│   ├── MyLinkedList.java         # Linked list implementation
│   ├── MySynchronizedList.java   # Thread-safe implementation
│   ├── ListType.java             # Enum for list types
│   ├── LoggingDecorator.java     # Decorator implementation
│   ├── Car.java                  # Builder pattern implementation
│   ├── Sound.java                # Sound system enum
│   ├── Navigation.java           # Navigation system enum
│   └── Air.java                  # Air conditioning enum
├── test/                         # Test files
│   ├── TestExercise1.java        # Factory Method tests
│   ├── TestExercise2.java        # Builder pattern tests
│   ├── TestExercise3.java        # Decorator pattern tests
│   └── TestAllExercises.java     # Master test runner
├── run_exercises.sh              # Unix/macOS run script
├── run_exercises.bat             # Windows run script
├── README.md                     # This file
├── EXERCISE1_FACTORY_METHOD.md   # Detailed Exercise 1 documentation
├── EXERCISE2_BUILDER_PATTERN.md  # Detailed Exercise 2 documentation
└── EXERCISE3_DECORATOR_PATTERN.md # Detailed Exercise 3 documentation
```

## 🚀 Quick Start

### Prerequisites
- Java Development Kit (JDK) 8 or higher
- Command line interface (Terminal/Command Prompt)

### Running All Exercises

**Unix/macOS/Linux:**
```bash
cd lab1
chmod +x run_exercises.sh
./run_exercises.sh
```

**Windows:**
```cmd
cd lab1
run_exercises.bat
```

### Running Individual Exercises

```bash
# Exercise 1 - Factory Method
./run_exercises.sh 1

# Exercise 2 - Builder Pattern
./run_exercises.sh 2

# Exercise 3 - Decorator Pattern
./run_exercises.sh 3

# Show help
./run_exercises.sh help
```

## 📚 Exercise Details

### Exercise 1: Factory Method Pattern
**File**: `EXERCISE1_FACTORY_METHOD.md`

Implements a static factory method that creates different list implementations:
- `MyArrayList` - Array-backed implementation
- `MyLinkedList` - Linked list implementation
- `MySynchronizedList` - Thread-safe wrapper

**Key Learning**: Object creation abstraction and polymorphism

### Exercise 2: Builder Pattern
**File**: `EXERCISE2_BUILDER_PATTERN.md`

Creates immutable `Car` objects with required and optional parameters:
- Required: brand, year, power, fuel type, chassis number
- Optional: sound system, navigation, air conditioning (with defaults)

**Key Learning**: Handling complex object construction with many optional parameters

### Exercise 3: Decorator Pattern
**File**: `EXERCISE3_DECORATOR_PATTERN.md`

Implements `LoggingDecorator` that adds logging behavior to any `MyList` implementation:
- Transparent wrapping of existing objects
- Runtime behavior extension
- Composable decorators

**Key Learning**: Dynamic behavior addition without inheritance

## 🎯 Design Pattern Benefits

| Pattern | Primary Benefit | Use Case |
|---------|----------------|----------|
| **Factory Method** | Decouples object creation | When you need different implementations of the same interface |
| **Builder** | Handles complex construction | When objects have many optional parameters |
| **Decorator** | Runtime behavior extension | When you need to add features without modifying existing code |

## ✅ Testing

Each exercise includes comprehensive tests demonstrating:
- ✅ Basic functionality
- ✅ Edge cases and error handling
- ✅ Pattern-specific benefits
- ✅ Integration with other components

**Test Coverage:**
- Exercise 1: Multiple list types, bounds checking, polymorphic usage
- Exercise 2: Required vs optional parameters, default values, immutability
- Exercise 3: Behavior addition, transparency, decorator chaining

## 🔧 Manual Compilation (Optional)

If you prefer manual compilation:

```bash
# Compile all files
javac -cp src:test src/*.java test/*.java

# Run specific tests
java -cp src:test TestExercise1
java -cp src:test TestExercise2
java -cp src:test TestExercise3

# Run all tests
java -cp src:test TestAllExercises
```

## 📖 Learning Objectives

Upon completion, you will understand:

1. **When and why** to use each design pattern
2. **How to implement** factory methods for object creation
3. **How to build** complex objects with optional parameters
4. **How to extend** object behavior without inheritance
5. **Best practices** for writing maintainable, extensible code

## 🌟 Key Takeaways

- **Factory Method**: "Don't call us, we'll call you" - Let the factory decide which implementation to create
- **Builder**: "Rome wasn't built in a day" - Complex objects are best built step by step
- **Decorator**: "You can't always get what you want, but you can always add what you need" - Extend behavior dynamically

## 📝 Additional Resources

- [Refactoring Guru - Design Patterns](https://refactoring.guru/design-patterns)
- [Gang of Four Design Patterns](https://en.wikipedia.org/wiki/Design_Patterns)
- [Effective Java by Joshua Bloch](https://www.amazon.com/Effective-Java-Joshua-Bloch/dp/0134685997)

---

**Course**: Software Design Techniques
**Lab**: Design Patterns Lab 1
**Patterns Covered**: Factory Method, Builder, Decorator