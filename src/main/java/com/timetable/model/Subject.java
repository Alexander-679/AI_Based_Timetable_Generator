package com.timetable.model;

import jakarta.persistence.*;

@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private int sessionsPerWeek;

    public Subject() {}

    public Subject(String name, int sessionsPerWeek) {
        this.name = name;
        this.sessionsPerWeek = sessionsPerWeek;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getSessionsPerWeek() {
        return sessionsPerWeek;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSessionsPerWeek(int sessionsPerWeek) {
        this.sessionsPerWeek = sessionsPerWeek;
    }
}
