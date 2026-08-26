package com.example.freetimematching;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.Objects;

public class ClassTime {
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public ClassTime(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "요일은 null일 수 없습니다.");
        this.startTime = Objects.requireNonNull(startTime, "시작 시간은 null일 수 없습니다.");
        this.endTime = Objects.requireNonNull(endTime, "종료 시간은 null일 수 없습니다.");

        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }


    //다른 수업시간 other를 받아서 겹치면 true, 안 겹치면 false 반환
    public boolean overlaps(ClassTime other) {
        Objects.requireNonNull(other, "비교할 수업 시간은 null일 수 없습니다.");

        if (dayOfWeek != other.dayOfWeek) {
            return false;
        }

        return startTime.isBefore(other.endTime)
                && other.startTime.isBefore(endTime);
    }
}
