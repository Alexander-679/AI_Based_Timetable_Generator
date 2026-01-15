package com.timetable.model;

import jakarta.persistence.*;

@Entity
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String subject; // simplified

    public Teacher() {}

    public Teacher(String name, String subject) {
        this.name = name;
        this.subject = subject;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSubject() {
        return subject;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }
}
