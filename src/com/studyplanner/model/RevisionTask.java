package com.studyplanner.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class RevisionTask extends StudyTask {
    public RevisionTask(String title, LocalDate deadline, int priority) {
        super(title, deadline, priority);
    }

    @Override
    public String getTaskType() { return "REVISION"; }

    @Override
    public int computeScore() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getDeadline());
        return (int)(getPriority() * 10 - daysLeft);
    }
}