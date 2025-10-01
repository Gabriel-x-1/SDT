# Exercise 1: Factory Method Pattern

## Overview
The Factory Method pattern provides an interface for creating objects without specifying their exact class. Instead of calling a constructor directly, you call a factory method that returns an instance of the appropriate class.

## Problem Solved
- **Decoupling object creation**: Client code doesn't need to know which specific implementation to instantiate
- **Extensibility**: New implementations can be added without modifying existing client code
- **Centralized creation logic**: All object creation logic is centralized in one place

## Implementation

### Core Components

#### 1. `MyList` Interface
```java
public interface MyList {
    void add(int value);           // Add element to end of list
    int get(int index);           // Get element at index

    // Static Factory Method
    static MyList getList(ListType type) {
        // Returns appropriate implementation based on type
    }
}
```

#### 2. `ListType` Enum
```java
public enum ListType {
    Array,      // Array-based implementation
    LinkedList, // Linked list implementation
    SyncList    // Thread-safe implementation
}
```

#### 3. Concrete Implementations

**MyArrayList**:
- Backed by a resizable array
- Fast random access (O(1))
- Dynamic resizing when capacity exceeded

**MyLinkedList**:
- Backed by a doubly-linked list structure
- Dynamic size, no pre-allocation needed
- Sequential access (O(n) for random access)

**MySynchronizedList**:
- Decorator around MyArrayList
- Thread-safe operations using `synchronized` keyword
- Suitable for multi-threaded environments

## Usage Example
```java
// Factory method creates appropriate implementation
MyList arrayList = MyList.getList(ListType.Array);
arrayList.add(5);
System.out.println(arrayList.get(0)); // Output: 5

MyList linkedList = MyList.getList(ListType.LinkedList);
linkedList.add(7);
System.out.println(linkedList.get(0)); // Output: 7

MyList syncList = MyList.getList(ListType.SyncList);
syncList.add(9);
System.out.println(syncList.get(0)); // Output: 9
```

## Benefits
1. **Polymorphism**: All implementations share the same interface
2. **Flexibility**: Easy to switch between implementations
3. **Maintainability**: Adding new list types doesn't break existing code
4. **Testability**: Easy to mock different implementations for testing

## Design Pattern Elements
- **Creator**: `MyList` interface with static factory method
- **Product**: `MyList` interface
- **Concrete Creators**: The factory method itself (using switch statement)
- **Concrete Products**: `MyArrayList`, `MyLinkedList`, `MySynchronizedList`

## Testing
Run the exercise with:
```bash
./run_exercises.sh 1
```

The test demonstrates:
- Creation of different list types via factory method
- Basic operations (add/get) work consistently across implementations
- Error handling (IndexOutOfBoundsException)
- Multiple element additions and retrievals