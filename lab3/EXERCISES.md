# Lab 3 - Exercise Descriptions

## Exercise 1: Object Pool + Singleton Pattern

**Objective:** Implement a pool of database connections with a maximum of 5 connections, controlled by a singleton factory.

**Implementation Details:**
- `DBConnection`: Mock connection class with unique ID
  - Output format: "Connection X was acquired by client Y" / "Connection X was released by client Y"
  - Not a real database connection - just mimics behavior
- `DBConnectionPool`: Singleton Object Pool that manages connections
  - Maintains available and in-use connections
  - Enforces max 5 connections limit
  - Thread-safe with wait/notify for blocking when pool is exhausted
  - Creates new connections directly (no prototype pattern)
- Concurrent testing with 10 clients competing for 5 connections

**Design Patterns Used:**
1. **Object Pool**: Reuses expensive resources (connections)
2. **Singleton**: One factory controls all connection creation
3. **Observer**: Logs connection lifecycle events

**Key Requirements Met:**
✅ Max 5 connections enforced  
✅ Thread-safe concurrent access  
✅ Proper acquire/release with blocking  
✅ Output shows connection ID and client ID  

---

## Exercise 2: Prototype Pattern

**Objective:** Create an RPG character creation system where characters can be created from scratch or cloned from prototypes.

**Implementation Details:**
- `Character`: RPG character with:
  - Attributes: STR, CON, DEX, INT, WIS, CHA (all 3-20 range)
  - Properties: name, class, story
  - Implements `Cloneable` for prototype pattern
  - Observable for tracking modifications
- `CharacterRegistry`: Singleton library of prototype characters
  - Predefined prototypes: Warrior (high STR), Wizard (high INT), Rogue (high DEX)
  - Users can clone and modify prototypes
- Interactive console application
  - Create from scratch or use predefined template
  - Modify any attribute
  - Display final character stats

**Design Patterns Used:**
1. **Prototype**: Clone existing characters to create new ones
2. **Singleton**: One registry for all prototypes
3. **Observer**: Log character modifications
4. **Object Pool**: Recycle blank Character objects

**Key Requirements Met:**
✅ No new classes created for different characters  
✅ Prototype cloning with `clone()` method  
✅ Library/map of predefined characters  
✅ Interactive character creation and modification  
✅ All stats validated (3-20 range)  

---

## Exercise 3: Observer Pattern

**Objective:** Implement a social network where users follow each other and receive notifications when followed users post statuses.

**Implementation Details:**
- `User`: Acts as both Subject and Observer
  - Can follow/unfollow other users
  - Posts statuses that notify all followers
  - Receives notifications from followed users
- Simulation with 5 users: Alice, Bob, Charlie, Diana, Eve
  - Predefined follower relationships
  - Random status posting
- Output format: "UserX was notified that UserY posted the status '...'"

**Design Patterns Used:**
1. **Observer**: Followers observe users they follow

**Key Requirements Met:**
✅ Follow/observe mechanism  
✅ Notification on status post  
✅ Mock implementation (no real database)  
✅ Random status generation  
✅ Console output with proper format  

---

## Grading Notes

**⚠️ First two exercises (1 & 2) are graded during the lab!**

### Exercise 1 Grading Points:
- Object Pool implementation with proper acquire/release
- Singleton enforcement of max connections
- Thread-safe concurrent access
- Proper output format with connection and client IDs

### Exercise 2 Grading Points:
- Prototype pattern (no new classes for characters)
- CharacterRegistry as prototype library
- Interactive console application
- Proper attribute validation
- Clear display of character stats

### Exercise 3 Grading Points:
- Observer pattern implementation
- Follow/notification mechanism
- Proper notification format
- Random status simulation

---

## Running the Exercises

### Quick Start
```bash
./start.sh
```

### Run Specific Exercise
```bash
./start.sh 1    # Object Pool + Singleton
./start.sh 2    # Prototype (Interactive)
./start.sh 3    # Observer Pattern
```

### Run All Non-Interactive
```bash
./start.sh --all
```

---

## Expected Output Examples

### Exercise 1
```
Connection 1 was acquired by client 1
Connection 2 was acquired by client 2
Connection 3 was acquired by client 3
Connection 4 was acquired by client 4
Connection 5 was acquired by client 5
Connection 1 was released by client 1
Connection 1 was acquired by client 6
...
All clients finished. Created connections: 5 (max 5)
```

### Exercise 2
```
=== RPG Character Creator ===

Options:
  1) Create from scratch
  2) Use predefined prototype
  3) Show prototypes
  4) Exit
```

### Exercise 3
```
[Alice posted: 'made omelette, looks delicious']
Bob was notified that Alice posted the status 'made omelette, looks delicious'
Charlie was notified that Alice posted the status 'made omelette, looks delicious'
Diana was notified that Alice posted the status 'made omelette, looks delicious'
```

---

*Lab 3 - Software Design Techniques*
*Design Patterns: Object Pool, Prototype, Observer*

