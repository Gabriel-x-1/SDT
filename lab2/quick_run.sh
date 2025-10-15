#!/bin/bash

# Quick runner for all exercises - no pauses
echo "🚀 Running all Lab 2 exercises..."

# Compile everything
javac -cp src/main/java src/main/java/*/*.java TestStrategy.java TestAdapter.java TestChainOfResponsibility.java

# Run all exercises
echo ""
echo "=== Exercise 1: Strategy Pattern ==="
java -cp .:src/main/java TestStrategy

echo ""
echo "=== Exercise 2: Adapter Pattern ==="
java -cp .:src/main/java TestAdapter

echo ""
echo "=== Exercise 3: Chain of Responsibility ==="
java -cp .:src/main/java TestChainOfResponsibility

echo ""
echo "✅ All exercises completed!"