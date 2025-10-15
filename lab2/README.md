# Lab 2: Design Patterns Implementation

## All Exercises Summary

### Exercise 1: Strategy Pattern ✅
**Pattern**: Strategy Pattern
**Problem**: Implement a sorting system with interchangeable comparison strategies
**Implementation**:
- `MyCollections` class with overloaded `sort()` methods
- `MyComparator<T>` interface for custom comparison strategies
- `IntegerAscendingComparator` as concrete strategy implementation

**Key Features**:
- Generic sorting for `Comparable` objects
- Custom sorting with `MyComparator` interface
- Bubble sort algorithm implementation
- Strategy pattern allows different sorting behaviors

**Test Results**: ✅ Successfully sorts arrays using both default Comparable and custom comparator strategies

---

### Exercise 2: Adapter Pattern ✅
**Pattern**: Adapter Pattern
**Problem**: Adapt existing `PersonalDataI` interface to new `PersonalInformation` JSON interface
**Implementation**:
- `PersonalDataI` interface (existing system) - individual getter methods
- `PersonalInformation` interface (client requirement) - JSON string output
- `PersonalDataAdapter` bridges the two interfaces
- `PersonalData` concrete implementation

**Key Features**:
- Interface adaptation without modifying existing code
- Data transformation (LocalDate → year, individual fields → JSON)
- Maintains compatibility with existing PersonalData implementations
- Clean JSON output format

**Test Results**: ✅ Successfully converts PersonalData objects to JSON format through adapter

---

### Exercise 3: Chain of Responsibility Pattern ✅
**Pattern**: Chain of Responsibility Pattern
**Problem**: Process sensor events through multiple notification handlers
**Implementation**:
- `NotificationHandler` abstract base class
- `EmailNotification`, `TelephoneNotification`, `Logger` concrete handlers
- `SensorEvent` with type, timestamp, location
- `SensorEventGenerator` thread for continuous event production

**Key Features**:
- Event processing chain: Email → Telephone → Logger
- Different handlers for different event types:
  - EmailNotification: Fire, Intrusion, Water
  - TelephoneNotification: Fire, Intrusion only
  - Logger: All events
- Thread-based continuous event generation
- Interactive system with graceful shutdown

**Test Results**: ✅ Events properly routed through chain based on event type

---

## Running the Exercises

### Compile All
```bash
cd lab2
javac -cp src/main/java src/main/java/*/*.java
```

### Run Individual Tests
```bash
# Strategy Pattern
java -cp .:src/main/java TestStrategy

# Adapter Pattern
java -cp .:src/main/java TestAdapter

# Chain of Responsibility
java -cp .:src/main/java TestChainOfResponsibility

# Full sensor monitoring system (interactive)
java -cp .:src/main/java chainofresponsibility.SensorMonitoringSystem
```

## Design Pattern Benefits Demonstrated

1. **Strategy Pattern**: Encapsulates algorithms and makes them interchangeable
2. **Adapter Pattern**: Enables interface compatibility without code modification
3. **Chain of Responsibility**: Decouples senders from receivers, allows multiple handlers

## File Structure
```
lab2/
├── src/main/java/
│   ├── strategy/          # Exercise 1
│   ├── adapter/           # Exercise 2
│   └── chainofresponsibility/ # Exercise 3
├── src/test/java/         # JUnit tests
├── TestStrategy.java      # Demo runner
├── TestAdapter.java       # Demo runner
├── TestChainOfResponsibility.java # Demo runner
└── *.md                   # Design documentation
```