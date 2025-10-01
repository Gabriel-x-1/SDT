#!/bin/bash

# Design Patterns Lab 1 - Exercise Runner Script
# This script compiles and runs all design pattern exercises

echo "=========================================="
echo "     DESIGN PATTERNS LAB 1 RUNNER        "
echo "=========================================="

# Set the base directory to the script's location
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

echo "Working directory: $(pwd)"
echo ""

# Function to run a specific exercise
run_exercise() {
    local exercise_num=$1
    local class_name=$2

    echo "=========================================="
    echo "         RUNNING EXERCISE $exercise_num"
    echo "=========================================="

    if java -cp src:test "$class_name"; then
        echo ""
        echo "✅ Exercise $exercise_num completed successfully!"
    else
        echo ""
        echo "❌ Exercise $exercise_num failed!"
        return 1
    fi
    echo ""
}

# Function to compile all files
compile_all() {
    echo "🔨 Compiling all Java files..."
    if javac -cp src:test src/*.java test/*.java; then
        echo "✅ Compilation successful!"
        echo ""
        return 0
    else
        echo "❌ Compilation failed!"
        echo ""
        return 1
    fi
}

# Main execution
main() {
    # Check if Java is installed
    if ! command -v javac &> /dev/null; then
        echo "❌ Error: Java compiler (javac) not found!"
        echo "Please install Java Development Kit (JDK) and try again."
        exit 1
    fi

    if ! command -v java &> /dev/null; then
        echo "❌ Error: Java runtime (java) not found!"
        echo "Please install Java and try again."
        exit 1
    fi

    echo "☕ Java version:"
    java -version
    echo ""

    # Compile all files
    if ! compile_all; then
        exit 1
    fi

    # Check command line arguments
    case "${1:-all}" in
        "1"|"exercise1")
            run_exercise 1 "TestExercise1"
            ;;
        "2"|"exercise2")
            run_exercise 2 "TestExercise2"
            ;;
        "3"|"exercise3")
            run_exercise 3 "TestExercise3"
            ;;
        "all"|"")
            echo "🚀 Running all exercises..."
            echo ""
            if java -cp src:test TestAllExercises; then
                echo ""
                echo "🎉 All exercises completed successfully!"
            else
                echo ""
                echo "❌ Some exercises failed!"
                exit 1
            fi
            ;;
        "help"|"-h"|"--help")
            echo "Usage: $0 [option]"
            echo ""
            echo "Options:"
            echo "  1, exercise1    Run only Exercise 1 (Factory Method Pattern)"
            echo "  2, exercise2    Run only Exercise 2 (Builder Pattern)"
            echo "  3, exercise3    Run only Exercise 3 (Decorator Pattern)"
            echo "  all, (default)  Run all exercises"
            echo "  help, -h        Show this help message"
            echo ""
            echo "Examples:"
            echo "  $0              # Run all exercises"
            echo "  $0 all          # Run all exercises"
            echo "  $0 1            # Run only exercise 1"
            echo "  $0 exercise2    # Run only exercise 2"
            ;;
        *)
            echo "❌ Unknown option: $1"
            echo "Use '$0 help' for usage information."
            exit 1
            ;;
    esac
}

# Run main function with all arguments
main "$@"