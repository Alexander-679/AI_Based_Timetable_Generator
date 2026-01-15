package com.timetable.service;

import com.timetable.dto.TimetableInputDTO;
import com.timetable.model.*;
import com.timetable.repository.*;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TimetableService {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final RoomRepository roomRepository;
    private final TimeSlotRepository timeSlotRepository;
    private final TimetableRepository timetableRepository;

    public TimetableService(
            SubjectRepository subjectRepository,
            TeacherRepository teacherRepository,
            RoomRepository roomRepository,
            TimeSlotRepository timeSlotRepository,
            TimetableRepository timetableRepository) {

        this.subjectRepository = subjectRepository;
        this.teacherRepository = teacherRepository;
        this.roomRepository = roomRepository;
        this.timeSlotRepository = timeSlotRepository;
        this.timetableRepository = timetableRepository;
    }

    // =========================
    // Save User Input (NO SQL)
    // =========================
    @Transactional
    public void saveUserInput(TimetableInputDTO input) {

        // Clear old data
        timetableRepository.deleteAll();
        subjectRepository.deleteAll();
        teacherRepository.deleteAll();
        roomRepository.deleteAll();
        timeSlotRepository.deleteAll();

        // Save subjects
        input.subjects.forEach(s ->
                subjectRepository.save(
                        new Subject(s.name, s.sessionsPerWeek)
                )
        );

        // Save teachers
        input.teachers.forEach(t ->
                teacherRepository.save(
                        new Teacher(t.name, t.subject)
                )
        );

        // Save rooms
        input.rooms.forEach(r ->
                roomRepository.save(new Room(r))
        );

        // Save time slots
        input.timeSlots.forEach(ts ->
                timeSlotRepository.save(
                        new TimeSlot(
                                ts.day,
                                ts.startTime,
                                ts.endTime,
                                ts.isBreak
                        )
                )
        );
    }

    // =========================
    // AI Timetable Generator
    // =========================
    @Transactional
    public List<Timetable> generateTimetable() {

        timetableRepository.deleteAll();

        List<Subject> subjects = subjectRepository.findAll();
        List<Teacher> teachers = teacherRepository.findAll();
        List<Room> rooms = roomRepository.findAll();
        List<TimeSlot> timeSlots = timeSlotRepository.findAll();

        List<Timetable> timetableList = new ArrayList<>();

        for (Subject subject : subjects) {

            int sessions = subject.getSessionsPerWeek();

            for (int i = 0; i < sessions; i++) {

                boolean assigned = false;

                for (TimeSlot slot : timeSlots) {

                    if (slot.isBreak()) continue;

                    Teacher teacher = findTeacherForSubject(subject, teachers);
                    Room room = findFreeRoom(slot, rooms, timetableList);

                    if (teacher != null
                            && room != null
                            && isTeacherFree(teacher, slot, timetableList)) {

                        timetableList.add(
                                new Timetable(subject, teacher, room, slot)
                        );
                        assigned = true;
                        break;
                    }
                }

                if (!assigned) {
                    System.out.println("Could not assign: " + subject.getName());
                }
            }
        }

        return timetableRepository.saveAll(timetableList);
    }

    // =========================
    // FETCH ONLY (IMPORTANT)
    // =========================
    public List<Timetable> getAllTimetables() {
        return timetableRepository.findAll();
    }

    // =========================
    // Helper Methods
    // =========================

    private Teacher findTeacherForSubject(Subject subject, List<Teacher> teachers) {
        return teachers.stream()
                .filter(t -> t.getSubject().equalsIgnoreCase(subject.getName()))
                .findFirst()
                .orElse(null);
    }

    private boolean isTeacherFree(Teacher teacher, TimeSlot slot, List<Timetable> timetableList) {
        return timetableList.stream()
                .noneMatch(t ->
                        t.getTeacher().getId().equals(teacher.getId())
                                && t.getTimeSlot().getId().equals(slot.getId())
                );
    }

    private Room findFreeRoom(TimeSlot slot, List<Room> rooms, List<Timetable> timetableList) {
        for (Room room : rooms) {
            boolean occupied = timetableList.stream()
                    .anyMatch(t ->
                            t.getRoom().getId().equals(room.getId())
                                    && t.getTimeSlot().getId().equals(slot.getId())
                    );
            if (!occupied) return room;
        }
        return null;
    }
}
