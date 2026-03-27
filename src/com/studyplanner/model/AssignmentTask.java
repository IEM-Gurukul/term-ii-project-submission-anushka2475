package com.studyplanner.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class AssignmentTask extends StudyTask {
    private int weightPercent;

    public AssignmentTask(String title, LocalDate deadline, int priority, int weightPercent) {
        super(title, deadline, priority);
        if (weightPercent < 0 || weightPercent > 100)
            throw new IllegalArgumentException("Weight must be between 0 and 100.");
        this.weightPercent = weightPercent;
    }

    public int getWeightPercent() { return weightPercent; }

    @Override
    public String getTaskType() { return "ASSIGNMENT"; }

    @Override
    public int computeScore() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getDeadline());
        return (int)(getPriority() * 10 + weightPercent - daysLeft);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + weightPercent;
    }
}