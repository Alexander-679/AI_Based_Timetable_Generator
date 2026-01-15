package com.timetable.model;

import jakarta.persistence.*;

@Entity
public class TimeSlot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String day;
    private String startTime;
    private String endTime;
    private boolean isBreak;

    public TimeSlot() {}

    public TimeSlot(String day, String startTime, String endTime, boolean isBreak) {
        this.day = day;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isBreak = isBreak;
    }

    public Long getId() {
        return id;
    }

    public String getDay() {
        return day;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public boolean isBreak() {
        return isBreak;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public void setBreak(boolean aBreak) {
        isBreak = aBreak;
    }
}
