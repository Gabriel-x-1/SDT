#!/bin/bash

# Lab 2 - Design Patterns Runner Script
# Make sure you're in the lab2 directory before running this script

echo "================================================================="
echo "          Lab 2: Design Patterns - All Exercises Runner"
echo "================================================================="

# Check if we're in the right directory
if [ ! -d "src" ]; then
    echo "❌ Error: Please run this script from the lab2 directory"
    echo "   Current directory: $(pwd)"
    echo "   Expected: .../SDT/lab2/"
    exit 1
fi

echo "📁 Current directory: $(pwd)"
echo "🔧 Compiling all Java classes..."

# Compile all classes
javac -cp src/main/java src/main/java/*/*.java TestStrategy.java TestAdapter.java TestChainOfResponsibility.java

if [ $? -ne 0 ]; then
    echo "❌ Compilation failed!"
    exit 1
fi

echo "✅ Compilation successful!"
echo ""

# Function to run an exercise with error handling
run_exercise() {
    local title="$1"
    local class_name="$2"

    echo "================================================================="
    echo "🚀 Running $title"
    echo "================================================================="

    java -cp .:src/main/java "$class_name"

    if [ $? -eq 0 ]; then
        echo "✅ $title completed successfully!"
    else
        echo "❌ $title failed!"
    fi

    echo ""
    echo "Press Enter to continue..."
    read -r
    echo ""
}

# Run all exercises
run_exercise "Exercise 1: Strategy Pattern" "TestStrategy"
run_exercise "Exercise 2: Adapter Pattern" "TestAdapter"
run_exercise "Exercise 3: Chain of Responsibility" "TestChainOfResponsibility"

echo "================================================================="
echo "🎯 All Exercises Completed!"
echo "================================================================="
echo ""
echo "Would you like to run the interactive sensor monitoring system? (y/n)"
read -r response

if [[ "$response" =~ ^[Yy]$ ]]; then
    echo ""
    echo "================================================================="
    echo "🔴 Starting Interactive Sensor Monitoring System"
    echo "   Press Ctrl+C or Enter in the application to stop"
    echo "================================================================="
    java -cp .:src/main/java chainofresponsibility.SensorMonitoringSystem
fi

echo ""
echo "================================================================="
echo "🎉 All done! Thanks for running Lab 2 exercises!"
echo "================================================================="