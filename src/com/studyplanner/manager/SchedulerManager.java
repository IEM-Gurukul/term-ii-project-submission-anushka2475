package com.studyplanner.manager;

import com.studyplanner.exception.InvalidTaskException;
import com.studyplanner.exception.TaskNotFoundException;
import com.studyplanner.model.StudyTask;
import com.studyplanner.storage.TaskStorage;

import java.util.*;

public class SchedulerManager {
    private final PriorityQueue<StudyTask> taskQueue;
    private final TaskStorage storage;

    public SchedulerManager() {
        this.taskQueue = new PriorityQueue<>(Collections.reverseOrder());
        this.storage = new TaskStorage();
        taskQueue.addAll(storage.loadTasks());
    }

    public void addTask(StudyTask task) throws InvalidTaskException {
        if (task == null) throw new InvalidTaskException("Cannot add a null task.");
        taskQueue.offer(task);
        storage.saveTasks(getAllTasks());
        System.out.println("✔ Task added: " + task.getTitle());
    }

    public void removeTask(String title) throws TaskNotFoundException {
        StudyTask toRemove = findTask(title);
        taskQueue.remove(toRemove);
        storage.saveTasks(getAllTasks());
        System.out.println("✔ Task removed: " + title);
    }

    public void markTaskComplete(String title) throws TaskNotFoundException {
        StudyTask task = findTask(title);
        task.markCompleted();
        taskQueue.remove(task);
        taskQueue.offer(task);
        storage.saveTasks(getAllTasks());
        System.out.println("✔ Marked complete: " + title);
    }

    public void displayTopTasks(int n) {
        List<StudyTask> sorted = getAllTasksSorted();
        System.out.println("\n--- Top " + n + " Priority Tasks ---");
        int count = 0;
        for (StudyTask task : sorted) {
            if (!task.isCompleted() && count < n) {
                System.out.println((count + 1) + ". " + task);
                count++;
            }
        }
        if (count == 0) System.out.println("No pending tasks.");
    }

    public void displayAllTasks() {
        List<StudyTask> sorted = getAllTasksSorted();
        System.out.println("\n--- All Tasks (Highest Priority First) ---");
        if (sorted.isEmpty()) {
            System.out.println("No tasks found.");
            return;
        }
        for (int i = 0; i < sorted.size(); i++) {
            System.out.println((i + 1) + ". " + sorted.get(i));
        }
    }

    private StudyTask findTask(String title) throws TaskNotFoundException {
        for (StudyTask task : taskQueue) {
            if (task.getTitle().equalsIgnoreCase(title)) return task;
        }
        throw new TaskNotFoundException(title);
    }

    public List<StudyTask> getAllTasks() {
        return new ArrayList<>(taskQueue);
    }

    private List<StudyTask> getAllTasksSorted() {
        List<StudyTask> list = new ArrayList<>(taskQueue);
        Collections.sort(list, Collections.reverseOrder());
        return list;
    }
}