@echo off
setlocal enabledelayedexpansion

REM Design Patterns Lab 1 - Exercise Runner Script (Windows)
REM This script compiles and runs all design pattern exercises

echo ==========================================
echo      DESIGN PATTERNS LAB 1 RUNNER
echo ==========================================

REM Set the base directory to the script's location
cd /d "%~dp0"

echo Working directory: %CD%
echo.

REM Function to run a specific exercise
REM Usage: call :run_exercise exercise_num class_name
goto :main

:run_exercise
set exercise_num=%1
set class_name=%2

echo ==========================================
echo          RUNNING EXERCISE %exercise_num%
echo ==========================================

java -cp src;test %class_name%
if %ERRORLEVEL% EQU 0 (
    echo.
    echo ✅ Exercise %exercise_num% completed successfully!
) else (
    echo.
    echo ❌ Exercise %exercise_num% failed!
    exit /b 1
)
echo.
goto :eof

REM Function to compile all files
:compile_all
echo 🔨 Compiling all Java files...
javac -cp src;test src\*.java test\*.java
if %ERRORLEVEL% EQU 0 (
    echo ✅ Compilation successful!
    echo.
    exit /b 0
) else (
    echo ❌ Compilation failed!
    echo.
    exit /b 1
)

REM Main execution
:main

REM Check if Java is installed
where javac >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Error: Java compiler ^(javac^) not found!
    echo Please install Java Development Kit ^(JDK^) and try again.
    exit /b 1
)

where java >nul 2>&1
if %ERRORLEVEL% NEQ 0 (
    echo ❌ Error: Java runtime ^(java^) not found!
    echo Please install Java and try again.
    exit /b 1
)

echo ☕ Java version:
java -version
echo.

REM Compile all files
call :compile_all
if %ERRORLEVEL% NEQ 0 exit /b 1

REM Check command line arguments
set arg=%1
if "%arg%"=="" set arg=all

if "%arg%"=="1" goto :exercise1
if "%arg%"=="exercise1" goto :exercise1
if "%arg%"=="2" goto :exercise2
if "%arg%"=="exercise2" goto :exercise2
if "%arg%"=="3" goto :exercise3
if "%arg%"=="exercise3" goto :exercise3
if "%arg%"=="all" goto :all_exercises
if "%arg%"=="help" goto :help
if "%arg%"=="-h" goto :help
if "%arg%"=="--help" goto :help

echo ❌ Unknown option: %arg%
echo Use '%~nx0 help' for usage information.
exit /b 1

:exercise1
call :run_exercise 1 "TestExercise1"
goto :end

:exercise2
call :run_exercise 2 "TestExercise2"
goto :end

:exercise3
call :run_exercise 3 "TestExercise3"
goto :end

:all_exercises
echo 🚀 Running all exercises...
echo.
java -cp src;test TestAllExercises
if %ERRORLEVEL% EQU 0 (
    echo.
    echo 🎉 All exercises completed successfully!
) else (
    echo.
    echo ❌ Some exercises failed!
    exit /b 1
)
goto :end

:help
echo Usage: %~nx0 [option]
echo.
echo Options:
echo   1, exercise1    Run only Exercise 1 ^(Factory Method Pattern^)
echo   2, exercise2    Run only Exercise 2 ^(Builder Pattern^)
echo   3, exercise3    Run only Exercise 3 ^(Decorator Pattern^)
echo   all, ^(default^)  Run all exercises
echo   help, -h        Show this help message
echo.
echo Examples:
echo   %~nx0              # Run all exercises
echo   %~nx0 all          # Run all exercises
echo   %~nx0 1            # Run only exercise 1
echo   %~nx0 exercise2    # Run only exercise 2
goto :end

:end
endlocal