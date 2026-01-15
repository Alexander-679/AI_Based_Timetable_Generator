package com.timetable.model;

import jakarta.persistence.*;

@Entity
public class Timetable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Subject subject;

    @ManyToOne
    private Teacher teacher;

    @ManyToOne
    private Room room;

    @ManyToOne
    private TimeSlot timeSlot;

    public Timetable() {}

    public Timetable(Subject subject, Teacher teacher, Room room, TimeSlot timeSlot) {
        this.subject = subject;
        this.teacher = teacher;
        this.room = room;
        this.timeSlot = timeSlot;
    }

    public Long getId() {
        return id;
    }

    public Subject getSubject() {
        return subject;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Room getRoom() {
        return room;
    }

    public TimeSlot getTimeSlot() {
        return timeSlot;
    }
}
