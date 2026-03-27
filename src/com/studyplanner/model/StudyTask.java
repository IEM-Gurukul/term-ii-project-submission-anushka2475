package com.studyplanner.model;

import java.time.LocalDate;

public abstract class StudyTask implements Comparable<StudyTask> {
    private String title;
    private LocalDate deadline;
    private int priority;
    private boolean completed;

    public StudyTask(String title, LocalDate deadline, int priority) {
        if (title == null || title.isBlank()) throw new IllegalArgumentException("Title cannot be empty.");
        if (deadline == null) throw new IllegalArgumentException("Deadline cannot be null.");
        if (priority < 1 || priority > 3) throw new IllegalArgumentException("Priority must be 1, 2, or 3.");
        this.title = title;
        this.deadline = deadline;
        this.priority = priority;
        this.completed = false;
    }

    public abstract String getTaskType();
    public abstract int computeScore();

    public String getTitle() { return title; }
    public LocalDate getDeadline() { return deadline; }
    public int getPriority() { return priority; }
    public boolean isCompleted() { return completed; }
    public void markCompleted() { this.completed = true; }
    public void setPriority(int priority) { this.priority = priority; }
    public void setDeadline(LocalDate deadline) { this.deadline = deadline; }

    @Override
    public int compareTo(StudyTask other) {
        return Integer.compare(this.computeScore(), other.computeScore());
    }

    @Override
    public String toString() {
        return String.format("[%s] %s | Due: %s | Priority: %d | Done: %s",
                getTaskType(), title, deadline, priority, completed ? "Yes" : "No");
    }

    public String toFileString() {
        return getTaskType() + "," + title + "," + deadline + "," + priority + "," + completed;
    }
}