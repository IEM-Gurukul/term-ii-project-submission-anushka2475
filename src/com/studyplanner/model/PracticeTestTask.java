package com.studyplanner.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class PracticeTestTask extends StudyTask {
    private String subject;

    public PracticeTestTask(String title, LocalDate deadline, int priority, String subject) {
        super(title, deadline, priority);
        this.subject = subject;
    }

    public String getSubject() { return subject; }

    @Override
    public String getTaskType() { return "PRACTICE_TEST"; }

    @Override
    public int computeScore() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), getDeadline());
        return (int)(getPriority() * 15 - daysLeft * 2);
    }

    @Override
    public String toFileString() {
        return super.toFileString() + "," + subject;
    }
}