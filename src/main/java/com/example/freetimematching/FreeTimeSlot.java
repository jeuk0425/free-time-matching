package com.example.freetimematching;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;

public class FreeTimeSlot {
    private static final LocalTime SEARCH_START_TIME = LocalTime.of(9, 0);
    private static final LocalTime SEARCH_END_TIME = LocalTime.of(18, 0);
    private static final Duration MINIMUM_DURATION = Duration.ofHours(1);

    private final TimeSlot timeSlot;

    public FreeTimeSlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.timeSlot = new TimeSlot(dayOfWeek, startTime, endTime);

        validateWeekday(dayOfWeek);
        validateTimeRange(startTime, endTime);
    }

    private void validateWeekday(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("공강은 월요일부터 금요일까지만 등록할 수 있습니다.");
        }
    }

    private void validateTimeRange(LocalTime startTime, LocalTime endTime) {
        if (startTime.isBefore(SEARCH_START_TIME) || endTime.isAfter(SEARCH_END_TIME)) {
            throw new IllegalArgumentException("공강 시간은 09:00부터 18:00 사이여야 합니다.");
        }

        if (Duration.between(startTime, endTime).compareTo(MINIMUM_DURATION) < 0) {
            throw new IllegalArgumentException("공강 시간은 최소 1시간 이상이어야 합니다.");
        }
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
}
