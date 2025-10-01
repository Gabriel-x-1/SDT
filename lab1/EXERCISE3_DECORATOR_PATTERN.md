# Exercise 3: Decorator Pattern

## Overview
The Decorator pattern allows you to dynamically add new behaviors to objects without altering their code. It provides a flexible alternative to subclassing for extending functionality by wrapping objects in useful wrapper classes.

## Problem Solved
- **Runtime Behavior Extension**: Add functionality to objects at runtime without modifying their source code
- **Alternative to Inheritance**: Avoid complex inheritance hierarchies for feature combinations
- **Composition over Inheritance**: Use object composition instead of inheritance to extend behavior
- **Open/Closed Principle**: Classes are open for extension but closed for modification

## Why Not Inheritance?

### Inheritance Approach (❌ Problematic)
```java
// Would need many subclasses for different combinations
class LoggingArrayList extends MyArrayList { ... }
class LoggingLinkedList extends MyLinkedList { ... }
class LoggingSyncList extends MySynchronizedList { ... }
class TimingArrayList extends MyArrayList { ... }
class LoggingTimingArrayList extends LoggingArrayList { ... }
// Exponential explosion of classes!
```

**Problems**:
- Class explosion for every combination
- Static behavior (set at compile time)
- Cannot combine multiple decorators easily
- Violates Single Responsibility Principle

### Decorator Approach (✅ Solution)
```java
// Flexible composition
MyList list = new LoggingDecorator(
    new TimingDecorator(
        MyList.getList(ListType.Array)
    )
);
```

## Implementation

### Core Components

#### 1. `LoggingDecorator` Class
```java
public class LoggingDecorator implements MyList {
    private final MyList list;  // Wrapped object

    public LoggingDecorator(MyList list) {
        this.list = list;  // Store reference to wrapped object
    }

    @Override
    public void add(int value) {
        list.add(value);  // Delegate to wrapped object
        System.out.println(value + " was added to the list");  // Add logging behavior
    }

    @Override
    public int get(int index) {
        return list.get(index);  // Simple delegation (no additional behavior)
    }
}
```

## Key Design Principles

### 1. **Same Interface**
- Decorator implements the same interface as the wrapped object
- Client code can use decorated and undecorated objects interchangeably

### 2. **Composition over Inheritance**
- Decorator contains (wraps) the original object instead of extending it
- Allows runtime behavior changes

### 3. **Delegation Pattern**
- Decorator delegates method calls to the wrapped object
- Adds its own behavior before, after, or around the delegation

### 4. **Transparency**
- Client doesn't know if it's working with original or decorated object
- Maintains the same interface contract

## Usage Examples

### Basic Logging
```java
// Wrap any MyList implementation with logging
LoggingDecorator loggedList = new LoggingDecorator(
    MyList.getList(ListType.Array)
);
loggedList.add(5);  // Output: "5 was added to the list"
```

### Multiple List Types
```java
// Works with any MyList implementation
LoggingDecorator loggedArray = new LoggingDecorator(
    MyList.getList(ListType.Array)
);

LoggingDecorator loggedLinked = new LoggingDecorator(
    MyList.getList(ListType.LinkedList)
);

LoggingDecorator loggedSync = new LoggingDecorator(
    MyList.getList(ListType.SyncList)
);
```

### Nested Decorators (Decorator Chain)
```java
// Multiple decorators can be chained
MyList baseList = MyList.getList(ListType.Array);
LoggingDecorator decorator1 = new LoggingDecorator(baseList);
LoggingDecorator decorator2 = new LoggingDecorator(decorator1);

decorator2.add(999);
// Output:
// "999 was added to the list"  (from decorator1)
// "999 was added to the list"  (from decorator2)
```

## Real-World Examples

### Java I/O Streams (Classic Example)
```java
// Multiple decorators wrapping a base stream
InputStream input = new BufferedInputStream(
    new DataInputStream(
        new FileInputStream("file.txt")
    )
);
```

### Coffee Shop Example
```java
// Base coffee with multiple add-ons
Coffee coffee = new MilkDecorator(
    new SugarDecorator(
        new VanillaDecorator(
            new BasicCoffee()
        )
    )
);
```

## Benefits

1. **Runtime Flexibility**: Add/remove behaviors at runtime
2. **Single Responsibility**: Each decorator has one specific purpose
3. **Composition**: Combine multiple decorators as needed
4. **No Class Explosion**: Avoid creating classes for every combination
5. **Open/Closed Principle**: Extend behavior without modifying existing code
6. **Transparent**: Client code remains unchanged

## Potential Extensions

The decorator pattern can be extended with additional decorators:

```java
// Timing decorator
class TimingDecorator implements MyList {
    private final MyList list;

    public void add(int value) {
        long start = System.currentTimeMillis();
        list.add(value);
        long end = System.currentTimeMillis();
        System.out.println("Add operation took: " + (end - start) + "ms");
    }
}

// Validation decorator
class ValidationDecorator implements MyList {
    private final MyList list;

    public void add(int value) {
        if (value < 0) {
            throw new IllegalArgumentException("Negative values not allowed");
        }
        list.add(value);
    }
}
```

## Design Pattern Elements

- **Component**: `MyList` interface (defines interface for objects that can have responsibilities added dynamically)
- **Concrete Component**: `MyArrayList`, `MyLinkedList`, etc. (base implementations)
- **Decorator**: `LoggingDecorator` (maintains reference to Component and implements same interface)
- **Concrete Decorator**: `LoggingDecorator` (adds specific behavior)

## Testing
Run the exercise with:
```bash
./run_exercises.sh 3
```

The test demonstrates:
- Basic logging functionality with different list types
- Transparent operation (get method works normally)
- Nested decorator behavior
- Compatibility with all MyList implementations