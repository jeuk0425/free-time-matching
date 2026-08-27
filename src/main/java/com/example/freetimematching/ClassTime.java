package com.example.freetimematching;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class ClassTime {
    private final TimeSlot timeSlot;
    
    public ClassTime(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.timeSlot = new TimeSlot(dayOfWeek, startTime, endTime);
    }

    public DayOfWeek getDayOfWeek() {
        return timeSlot.getDayOfWeek();
    }

    public LocalTime getStartTime() {
        return timeSlot.getStartTime();
    }

    public LocalTime getEndTime() {
        return timeSlot.getEndTime();
    }

    public boolean overlaps(ClassTime other) {
        Objects.requireNonNull(other, "비교할 수업 시간은 null일 수 없습니다.");
        return timeSlot.overlaps(other.timeSlot);
    }
}
