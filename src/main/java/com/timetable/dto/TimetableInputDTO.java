package com.timetable.dto;

import java.util.List;

public class TimetableInputDTO {

    public List<SubjectInput> subjects;
    public List<TeacherInput> teachers;
    public List<String> rooms;
    public List<TimeSlotInput> timeSlots;

    public static class SubjectInput {
        public String name;
        public int sessionsPerWeek;
    }

    public static class TeacherInput {
        public String name;
        public String subject;
    }

    public static class TimeSlotInput {
        public String day;
        public String startTime;
        public String endTime;
        public boolean isBreak;
    }
}
