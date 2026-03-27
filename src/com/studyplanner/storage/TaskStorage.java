package com.studyplanner.storage;

import com.studyplanner.model.*;
import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TaskStorage {
    private static final String FILE_PATH = "tasks.csv";

    public void saveTasks(List<StudyTask> tasks) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(FILE_PATH))) {
            for (StudyTask task : tasks) {
                writer.println(task.toFileString());
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks: " + e.getMessage());
        }
    }

    public List<StudyTask> loadTasks() {
        List<StudyTask> tasks = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists()) return tasks;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                StudyTask task = parseTask(line);
                if (task != null) tasks.add(task);
            }
        } catch (IOException e) {
            System.out.println("Error loading tasks: " + e.getMessage());
        }
        return tasks;
    }

    private StudyTask parseTask(String line) {
        try {
            String[] parts = line.split(",");
            String type = parts[0];
            String title = parts[1];
            LocalDate deadline = LocalDate.parse(parts[2]);
            int priority = Integer.parseInt(parts[3]);
            boolean completed = Boolean.parseBoolean(parts[4]);

            StudyTask task = switch (type) {
                case "REVISION" -> new RevisionTask(title, deadline, priority);
                case "ASSIGNMENT" -> new AssignmentTask(title, deadline, priority, Integer.parseInt(parts[5]));
                case "PRACTICE_TEST" -> new PracticeTestTask(title, deadline, priority, parts[5]);
                default -> null;
            };
            if (task != null && completed) task.markCompleted();
            return task;
        } catch (Exception e) {
            System.out.println("Skipping malformed line: " + line);
            return null;
        }
    }
}