#!/bin/bash

# start.sh - Run all Lab 3 exercises
# Design Patterns: Object Pool, Prototype, Observer

set -e  # Exit on error

LAB3_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$LAB3_DIR"

echo "==========================================="
echo "      Lab 3 - Design Patterns Demo"
echo "==========================================="
echo ""

# Colors for output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

# Create output directory for compiled classes
mkdir -p out

echo -e "${BLUE}Compiling all exercises...${NC}"
echo ""

# Compile Exercise 1 (Object Pool + Singleton)
echo "Compiling Exercise 1 (Object Pool + Singleton)..."
javac -d out src/lab3/*.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Exercise 1 compiled successfully${NC}"
else
    echo "Failed to compile Exercise 1"
    exit 1
fi

# Compile Exercise 2 (Prototype)
echo "Compiling Exercise 2 (Prototype Pattern)..."
javac -d out ex2/*.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Exercise 2 compiled successfully${NC}"
else
    echo "Failed to compile Exercise 2"
    exit 1
fi

# Compile Exercise 3 (Observer)
echo "Compiling Exercise 3 (Observer Pattern)..."
javac -d out ex3/*.java
if [ $? -eq 0 ]; then
    echo -e "${GREEN}✓ Exercise 3 compiled successfully${NC}"
else
    echo "Failed to compile Exercise 3"
    exit 1
fi

echo ""
echo -e "${GREEN}All exercises compiled successfully!${NC}"
echo ""

# Function to show menu
show_menu() {
    echo ""
    echo "==========================================="
    echo "           Select Exercise to Run"
    echo "==========================================="
    echo "1) Exercise 1 - Object Pool + Singleton (DBConnection Pool)"
    echo "2) Exercise 2 - Prototype Pattern (RPG Character Creator)"
    echo "3) Exercise 3 - Observer Pattern (Social Network)"
    echo "4) Run All Exercises (non-interactive only)"
    echo "5) Exit"
    echo "==========================================="
    echo -n "Enter your choice [1-5]: "
}

# Function to run exercise 1
run_exercise1() {
    echo ""
    echo -e "${YELLOW}==========================================="
    echo "  Exercise 1: Object Pool + Singleton"
    echo "  DBConnection Pool with Concurrent Clients"
    echo "===========================================${NC}"
    echo ""
    java -cp out lab3.Main
    echo ""
    echo -e "${GREEN}Exercise 1 completed.${NC}"
}

# Function to run exercise 2
run_exercise2() {
    echo ""
    echo -e "${YELLOW}==========================================="
    echo "  Exercise 2: Prototype Pattern"
    echo "  RPG Character Creator (Interactive)"
    echo "===========================================${NC}"
    echo ""
    java -cp out ex2.Main
    echo ""
    echo -e "${GREEN}Exercise 2 completed.${NC}"
}

# Function to run exercise 3
run_exercise3() {
    echo ""
    echo -e "${YELLOW}==========================================="
    echo "  Exercise 3: Observer Pattern"
    echo "  Social Network Status Updates"
    echo "===========================================${NC}"
    echo ""
    java -cp out ex3.Main
    echo ""
    echo -e "${GREEN}Exercise 3 completed.${NC}"
}

# Function to run all non-interactive exercises
run_all() {
    run_exercise1
    echo ""
    echo "Skipping Exercise 2 (interactive)..."
    echo ""
    run_exercise3
    echo ""
    echo -e "${GREEN}All non-interactive exercises completed!${NC}"
}

# Main menu loop
if [ "$1" == "--all" ] || [ "$1" == "-a" ]; then
    run_all
    exit 0
fi

if [ "$1" == "1" ]; then
    run_exercise1
    exit 0
elif [ "$1" == "2" ]; then
    run_exercise2
    exit 0
elif [ "$1" == "3" ]; then
    run_exercise3
    exit 0
fi

# Interactive menu
while true; do
    show_menu
    read choice
    
    case $choice in
        1)
            run_exercise1
            ;;
        2)
            run_exercise2
            ;;
        3)
            run_exercise3
            ;;
        4)
            run_all
            ;;
        5)
            echo ""
            echo "Exiting..."
            exit 0
            ;;
        *)
            echo -e "${YELLOW}Invalid option. Please choose 1-5.${NC}"
            ;;
    esac
done

