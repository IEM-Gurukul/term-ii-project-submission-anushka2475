package com.studyplanner.app;

import com.studyplanner.exception.InvalidTaskException;
import com.studyplanner.exception.TaskNotFoundException;
import com.studyplanner.manager.SchedulerManager;
import com.studyplanner.model.*;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);
    private static final SchedulerManager manager = new SchedulerManager();

    public static void main(String[] args) {
        System.out.println("=== Smart Study Planner ===");
        boolean running = true;
        while (running) {
            printMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> addRevisionTask();
                case "2" -> addAssignmentTask();
                case "3" -> addPracticeTest();
                case "4" -> manager.displayAllTasks();
                case "5" -> manager.displayTopTasks(3);
                case "6" -> markComplete();
                case "7" -> removeTask();
                case "0" -> { System.out.println("Goodbye!"); running = false; }
                default -> System.out.println("Invalid option. Try again.");
            }
        }
    }

    private static void printMenu() {
        System.out.println("""
\n--- MENU ---
1. Add Revision Task
2. Add Assignment Task
3. Add Practice Test
4. View All Tasks
5. View Top 3 Priority Tasks
6. Mark Task Complete
7. Remove Task
0. Exit""");
        System.out.print("Choice: ");
    }

    private static void addRevisionTask() {
        try {
            System.out.print("Title: "); String title = scanner.nextLine();
            System.out.print("Deadline (YYYY-MM-DD): "); LocalDate deadline = LocalDate.parse(scanner.nextLine());
            System.out.print("Priority (1=High, 2=Medium, 3=Low): "); int p = Integer.parseInt(scanner.nextLine());
            manager.addTask(new RevisionTask(title, deadline, p));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (InvalidTaskException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addAssignmentTask() {
        try {
            System.out.print("Title: "); String title = scanner.nextLine();
            System.out.print("Deadline (YYYY-MM-DD): "); LocalDate deadline = LocalDate.parse(scanner.nextLine());
            System.out.print("Priority (1=High, 2=Medium, 3=Low): "); int p = Integer.parseInt(scanner.nextLine());
            System.out.print("Grade Weight % (e.g. 20): "); int w = Integer.parseInt(scanner.nextLine());
            manager.addTask(new AssignmentTask(title, deadline, p, w));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (InvalidTaskException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void addPracticeTest() {
        try {
            System.out.print("Title: "); String title = scanner.nextLine();
            System.out.print("Deadline (YYYY-MM-DD): "); LocalDate deadline = LocalDate.parse(scanner.nextLine());
            System.out.print("Priority (1=High, 2=Medium, 3=Low): "); int p = Integer.parseInt(scanner.nextLine());
            System.out.print("Subject: "); String subject = scanner.nextLine();
            manager.addTask(new PracticeTestTask(title, deadline, p, subject));
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format. Use YYYY-MM-DD.");
        } catch (InvalidTaskException | IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void markComplete() {
        System.out.print("Enter task title to mark complete: ");
        String title = scanner.nextLine();
        try { manager.markTaskComplete(title); }
        catch (TaskNotFoundException e) { System.out.println("Error: " + e.getMessage()); }
    }

    private static void removeTask() {
        System.out.print("Enter task title to remove: ");
        String title = scanner.nextLine();
        try { manager.removeTask(title); }
        catch (TaskNotFoundException e) { System.out.println("Error: " + e.getMessage()); }
    }
}