[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/pG3gvzt-)
# Smart Study Planner

## Project Title
Smart Study Planner with Priority-Based Scheduling

## Problem Statement
Students struggle to manage multiple deadlines with varying urgency and complexity. Most to-do apps rely on manual prioritization and do not dynamically adjust task order when deadlines change. This leads to missed commitments and poor planning. The Smart Study Planner solves this by modeling tasks through an object-oriented hierarchy. Each task encapsulates priority, deadline, and type. A priority queue automatically reorders tasks as conditions change, demonstrating clear abstraction, modularity, and extensible design.

## Target User
College students managing academic deadlines, assignments, and revision schedules.

## Core Features
- Add Revision, Assignment, and Practice Test tasks
- Automatic priority-based ordering using Priority Queue
- Persistent file storage (tasks saved between sessions)
- Mark tasks as complete
- View top 3 priority pending tasks
- Robust exception handling for invalid inputs

## OOP Concepts Used
- Abstraction: Abstract `StudyTask` base class defining shared behavior
- Inheritance: `RevisionTask`, `AssignmentTask`, `PracticeTestTask` extend `StudyTask`
- Polymorphism: `computeScore()` overridden differently in each subclass
- Exception Handling: Custom `InvalidTaskException`, `TaskNotFoundException`
- Collections: `PriorityQueue` for dynamic priority-based ordering

## Proposed Architecture Description
The system follows a layered object-oriented architecture. An abstract StudyTask class defines common behavior, and specialized subclasses implement specific task types. A central SchedulerManager coordinates task prioritization using a PriorityQueue. A separate TaskStorage layer handles file persistence, ensuring clear separation between domain logic and data management.

## How to Run
1. Open project in IntelliJ IDEA
2. Right-click `Main.java` → Run 'Main.main()'
3. Use the console menu to add and manage tasks
4. Tasks are automatically saved to tasks.csv

## Git Discipline Notes
Minimum 10 meaningful commits required.
