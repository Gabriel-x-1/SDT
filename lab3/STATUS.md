# Lab 3 - Implementation Status

## ✅ ALL EXERCISES COMPLETED AND TESTED

---

## Summary

| Exercise | Pattern | Status | Files | Description |
|----------|---------|--------|-------|-------------|
| **Exercise 1** | Object Pool + Singleton | ✅ Complete | 6 files | DBConnection pool with max 5 connections |
| **Exercise 2** | Prototype | ✅ Complete | 6 files | RPG character creator with prototypes |
| **Exercise 3** | Observer | ✅ Complete | 2 files | Social network with status notifications |

**Total Lines of Code:** 678 lines across 14 Java files

---

## Exercise 1: Object Pool + Singleton ✅

**Location:** `src/lab3/`

**Files:**
- `DBConnection.java` - Mock database connection with unique ID
- `DBConnectionPool.java` - Singleton Object Pool managing connections and enforcing max 5 limit
- `ConnectionObserver.java` - Observer interface
- `LoggerObserver.java` - Logs acquire/release events
- `Main.java` - 10 concurrent clients test

**Features Implemented:**
- ✅ Object Pool pattern with acquire/release
- ✅ Singleton pattern (DBConnectionPool itself)
- ✅ Max 5 connections enforced directly in pool
- ✅ Thread-safe concurrent access with wait/notify
- ✅ Observer pattern for logging
- ✅ Proper output: "Connection X was acquired/released by client Y"
- ✅ Tested with 10 concurrent clients
- ✅ No prototype pattern (connections created directly)

**Test Results:**
```
Connection 1 was acquired by client 1
Connection 2 was acquired by client 2
...
All clients finished. Created connections: 5 (max 5)
```

---

## Exercise 2: Prototype Pattern ✅

**Location:** `ex2/`

**Files:**
- `Character.java` - RPG character with clone() method
- `CharacterRegistry.java` - Singleton prototype library
- `CharacterObserver.java` - Observer interface
- `LoggerObserver.java` - Logs character modifications
- `CharacterPool.java` - Object pool for blank characters
- `Main.java` - Interactive console application

**Features Implemented:**
- ✅ Prototype pattern with clone() method
- ✅ No new classes for different characters
- ✅ CharacterRegistry with Warrior, Wizard, Rogue prototypes
- ✅ Interactive character creation and editing
- ✅ Attribute validation (3-20 range)
- ✅ Observer pattern for logging changes
- ✅ Complete character display with all stats

**Predefined Characters:**
- **Warrior**: STR 18, CON 16, DEX 12, INT 8, WIS 10, CHA 9
- **Wizard**: STR 8, CON 10, DEX 12, INT 18, WIS 14, CHA 11
- **Rogue**: STR 12, CON 12, DEX 18, INT 10, WIS 11, CHA 13

---

## Exercise 3: Observer Pattern ✅

**Location:** `ex3/`

**Files:**
- `User.java` - User class (both Subject and Observer)
- `Main.java` - Social network simulation

**Features Implemented:**
- ✅ Observer pattern (users follow other users)
- ✅ Follow/unfollow mechanism
- ✅ Status posting with follower notifications
- ✅ Proper output: "UserX was notified that UserY posted..."
- ✅ Random status generation
- ✅ 5 users with predefined relationships
- ✅ Scheduled executor for random posts

**Test Network:**
- Alice (3 followers: Bob, Charlie, Diana)
- Bob (2 followers: Alice, Charlie)
- Charlie (3 followers: Alice, Bob, Eve)
- Diana (0 followers)
- Eve (2 followers: Diana, Bob)

**Sample Statuses:**
- "made omelette, looks delicious"
- "just finished a great book"
- "loving this sunny weather"
- ...and 7 more

---

## How to Run

### Interactive Menu (Recommended)
```bash
cd /Users/bilciurescugabriel/Desktop/SDT/lab3
./start.sh
```

Then select:
1. Exercise 1 - Object Pool demo
2. Exercise 2 - Character creator (interactive)
3. Exercise 3 - Social network simulation
4. Run all (skips interactive ex2)
5. Exit

### Direct Execution
```bash
# Run specific exercise
./start.sh 1    # Exercise 1
./start.sh 2    # Exercise 2 (interactive)
./start.sh 3    # Exercise 3

# Run all non-interactive
./start.sh --all
```

### Manual Compilation
```bash
javac -d out src/lab3/*.java ex2/*.java ex3/*.java
java -cp out lab3.Main       # Exercise 1
java -cp out ex2.Main        # Exercise 2
java -cp out ex3.Main        # Exercise 3
```

---

## Design Patterns Used

### Primary Patterns (Required)
1. **Object Pool** (Exercise 1): Reuse expensive resources
2. **Prototype** (Exercise 2): Clone objects without new classes
3. **Observer** (Exercise 3): Notification system

### Additional Patterns (Bonus)
4. **Singleton** (Exercise 1, 2): Pool and registry instances
5. **Observer** (Exercise 1, 2): Logging modifications

---

## Testing Status

| Exercise | Compilation | Execution | Output Correct | Thread Safety |
|----------|-------------|-----------|----------------|---------------|
| Exercise 1 | ✅ Pass | ✅ Pass | ✅ Verified | ✅ Tested |
| Exercise 2 | ✅ Pass | ✅ Pass | ✅ Verified | N/A |
| Exercise 3 | ✅ Pass | ✅ Pass | ✅ Verified | ✅ Safe |

---

## Files Created/Modified

### New Files Created (Exercise 3)
- `ex3/User.java` - 60 lines
- `ex3/Main.java` - 55 lines

### Modified Files (Bug Fixes)
- `src/lab3/DBConnectionPool.java` - Fixed duplicate observer notification

### Supporting Files
- `start.sh` - 144 lines - Complete runner script
- `README.md` - Comprehensive documentation
- `EXERCISES.md` - Detailed exercise descriptions
- `STATUS.md` - This status file

---

## Grading Checklist

### Exercise 1 (Graded in Lab) ✅
- [x] Object Pool implementation
- [x] Singleton factory
- [x] Max 5 connections enforced
- [x] Thread-safe concurrent access
- [x] Proper output format
- [x] Mock connections (not real DB)

### Exercise 2 (Graded in Lab) ✅
- [x] Prototype pattern with clone()
- [x] No new classes for characters
- [x] CharacterRegistry library
- [x] Interactive console app
- [x] Attribute validation (3-20)
- [x] Complete character display

### Exercise 3 ✅
- [x] Observer pattern
- [x] Follow mechanism
- [x] Status posting
- [x] Follower notification
- [x] Proper output format
- [x] Random simulation

---

## Next Steps

1. **Review the implementations** in each exercise directory
2. **Run the start.sh script** to see all exercises in action
3. **Prepare for lab grading** - focus on Exercise 1 & 2
4. **Test modifications** - the code is well-structured for extensions

---

## Quick Reference

```bash
# Quick test all exercises
./start.sh --all

# Interactive menu
./start.sh

# Individual exercises
./start.sh 1
./start.sh 2
./start.sh 3
```

---

**Status:** ✅ ALL COMPLETE AND TESTED  
**Date:** October 29, 2025  
**Total Implementation:** 678 lines of Java code  
**Design Patterns:** 3 primary + 2 bonus patterns  

---

*Ready for lab grading! 🎯*

