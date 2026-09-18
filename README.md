# Student Performance Management System

## Overview

Student Performance Management System is a Java-based command-line
application for managing student information and academic performance.

The application allows users to add, view, search, update and delete
student records. It also allows academic marks to be recorded and
performance reports to be generated.

## Features

### Student Management

- Add student
- View all students
- Search student
- Update student
- Delete student

### Performance Management

- Add subject marks
- Calculate total marks
- Calculate average marks
- Calculate grades
- View student performance

### Reports and Analytics

- Generate individual student report
- Display highest performance
- Display lowest performance
- Calculate overall average
- Display total students and performance records

### Data Management

- File-based data storage
- Automatic loading of saved data
- Automatic saving after changes

### Validation

- Student ID validation
- Name validation
- Semester validation
- Marks validation
- Invalid input handling

## Technologies Used

- Java
- Object-Oriented Programming
- Java Collections Framework
- File Handling
- Exception Handling
- Command-Line Interface

## Project Structure

```text
student-performance-management-system/
│
├── src/
│   ├── Main.java
│   ├── Student.java
│   ├── StudentManager.java
│   ├── Performance.java
│   ├── PerformanceManager.java
│   ├── FileManager.java
│   ├── ValidationUtil.java
│   ├── ReportManager.java
│   └── InputHelper.java
│
├── data/
│   ├── students.txt
│   └── performance.txt
│
├── tests/
│   └── ValidationTest.java
│
├── README.md
└── statement.md