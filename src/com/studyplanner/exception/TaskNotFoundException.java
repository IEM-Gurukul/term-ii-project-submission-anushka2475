package com.studyplanner.exception;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String title) {
        super("Task not found: " + title);
    }
}