# Lab 3 - Design Patterns

This lab implements three design patterns: **Object Pool**, **Prototype**, and **Observer**.

## Quick Start

```bash
./start.sh
```

This will compile all exercises and present an interactive menu.

### Command Line Options

```bash
./start.sh 1      # Run Exercise 1 only
./start.sh 2      # Run Exercise 2 only
./start.sh 3      # Run Exercise 3 only
./start.sh --all  # Run all non-interactive exercises
```

## Exercises

### Exercise 1: Object Pool + Singleton Pattern 🔵
**Location:** `src/lab3/`

**Design Patterns:**
- **Object Pool**: `DBConnectionPool` manages a pool of reusable `DBConnection` objects
- **Singleton**: `DBConnectionPool` itself is a Singleton that enforces max connections (5)

**Implementation:**
- `DBConnection`: Mimics a database connection with unique ID
- `DBConnectionPool`: Singleton Object Pool that manages available/in-use connections and enforces max 5 limit
- `ConnectionObserver`: Observer interface for connection events
- `LoggerObserver`: Logs when connections are acquired/released
- `Main`: Simulates 10 concurrent clients competing for 5 connections

**Features:**
- Thread-safe connection pool with wait/notify mechanism
- Concurrent client simulation using ExecutorService
- Observer pattern for logging connection events
- Output shows: "Connection X was acquired by client Y" / "Connection X was released by client Y"

**Run:**
```bash
java -cp out lab3.Main
```

---

### Exercise 2: Prototype Pattern 🎮
**Location:** `ex2/`

**Design Pattern:**
- **Prototype**: Characters can be cloned to create new characters without defining new classes
- **Singleton**: `CharacterRegistry` maintains a library of prototype characters

**Implementation:**
- `Character`: RPG character with attributes (STR, CON, DEX, INT, WIS, CHA), name, class, and story
- `CharacterRegistry`: Singleton library of predefined prototypes (Warrior, Wizard, Rogue)
- `CharacterObserver`: Observer interface for character modifications
- `LoggerObserver`: Logs all character attribute changes
- `CharacterPool`: Object pool for blank Character objects
- `Main`: Interactive console application for character creation

**Features:**
- Create characters from scratch or clone predefined prototypes
- All attributes validated (3-20 range)
- No new classes created for different characters (uses Prototype pattern)
- Interactive editing with observers logging changes
- Predefined prototypes: Warrior (high STR), Wizard (high INT), Rogue (high DEX)

**Run:**
```bash
java -cp out ex2.Main
```

**Usage:**
1. Choose "Create from scratch" or "Use predefined prototype"
2. Modify attributes interactively
3. View final character stats

---

### Exercise 3: Observer Pattern 📱
**Location:** `ex3/`

**Design Pattern:**
- **Observer**: Users observe other users they follow and get notified of status updates

**Implementation:**
- `User`: Acts as both Subject (observable) and Observer
  - Maintains list of followers
  - Posts statuses that notify all followers
  - Receives notifications from followed users
- `Main`: Simulates a social network with 5 users posting random statuses

**Features:**
- Users can follow/unfollow other users
- When a user posts a status, all followers are notified
- Console output: "UserX was notified that UserY posted the status '...'"
- Random status generation with scheduled executor
- Network relationship setup (Alice, Bob, Charlie, Diana, Eve)

**Run:**
```bash
java -cp out ex3.Main
```

**Example Output:**
```
[Alice posted: 'made omelette, looks delicious']
Bob was notified that Alice posted the status 'made omelette, looks delicious'
Charlie was notified that Alice posted the status 'made omelette, looks delicious'
Diana was notified that Alice posted the status 'made omelette, looks delicious'
```

---

## Project Structure

```
lab3/
├── start.sh                    # Main runner script
├── README.md                   # This file
├── out/                        # Compiled classes (generated)
├── src/lab3/                   # Exercise 1: Object Pool + Singleton
│   ├── DBConnection.java
│   ├── DBConnectionPool.java  (Singleton Object Pool)
│   ├── ConnectionObserver.java
│   ├── LoggerObserver.java
│   └── Main.java
├── ex2/                        # Exercise 2: Prototype
│   ├── Character.java
│   ├── CharacterRegistry.java
│   ├── CharacterObserver.java
│   ├── LoggerObserver.java
│   ├── CharacterPool.java
│   └── Main.java
└── ex3/                        # Exercise 3: Observer
    ├── User.java
    └── Main.java
```

## Design Patterns Summary

| Exercise | Primary Pattern | Additional Patterns | Key Classes |
|----------|----------------|---------------------|-------------|
| 1 | Object Pool | Singleton, Observer | `DBConnectionPool` (Singleton) |
| 2 | Prototype | Singleton, Observer, Object Pool | `Character`, `CharacterRegistry` |
| 3 | Observer | - | `User` |

## Requirements

- Java 8 or higher
- Bash shell (for start.sh)

## Grading Notes

**First two exercises (1 & 2) are graded during the lab!**

- Exercise 1 demonstrates concurrent access to pooled resources
- Exercise 2 shows character cloning without creating new classes
- Exercise 3 implements a social network notification system

## Manual Compilation (if needed)

```bash
# Compile Exercise 1
javac -d out src/lab3/*.java

# Compile Exercise 2
javac -d out ex2/*.java

# Compile Exercise 3
javac -d out ex3/*.java
```

## Testing

Each exercise includes a Main class with test scenarios:
- **Exercise 1**: 10 concurrent clients accessing 5-connection pool
- **Exercise 2**: Interactive character creation with multiple options
- **Exercise 3**: 5 users posting 15 random statuses with follower notifications

---

*Implemented for Software Design Techniques Lab 3*

