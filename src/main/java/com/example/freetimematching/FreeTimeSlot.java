package com.example.freetimematching;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Objects;

public class FreeTimeSlot {
    private static final LocalTime SEARCH_START_TIME = LocalTime.of(9, 0);
    private static final LocalTime SEARCH_END_TIME = LocalTime.of(18, 0);
    private static final Duration MINIMUM_DURATION = Duration.ofHours(1);

    /*
    ClassTime 필드와는 의미가다름, 이 필드는 공강시간을 계산하는필드 ClassTime은 수업시간을 계산하는 필드
    중복을 처음 보는 순간 추상화하지 말고, 중복의 이유가 같다는 확신이 생기면 추상화한다.
    */
    private final DayOfWeek dayOfWeek;
    private final LocalTime startTime;
    private final LocalTime endTime;

    public FreeTimeSlot(DayOfWeek dayOfWeek, LocalTime startTime, LocalTime endTime) {
        this.dayOfWeek = Objects.requireNonNull(dayOfWeek, "요일은 null일 수 없습니다.");
        // requireNonNull이랑 같은코드
        /* if (dayOfWeek == null) {
           throw new NullPointerException("요일은 null일 수 없습니다.");
        }*/
        this.startTime = Objects.requireNonNull(startTime, "시작 시간은 null일 수 없습니다.");
        this.endTime = Objects.requireNonNull(endTime, "종료 시간은 null일 수 없습니다.");

        validateWeekday(dayOfWeek); // FreeTimeSlot책임이다! 스스로 자신이 유효한공강시간인지 판단해야함! 잘못된상태의 객체가 애초에 만들어지지않게하자
        validateTimeRange(startTime, endTime);
    }

    private void validateWeekday(DayOfWeek dayOfWeek) {
        if (dayOfWeek == DayOfWeek.SATURDAY || dayOfWeek == DayOfWeek.SUNDAY) {
            throw new IllegalArgumentException("공강은 월요일부터 금요일까지만 등록할 수 있습니다.");
        }
    }

    private void validateTimeRange(LocalTime startTime, LocalTime endTime) {
        if (!startTime.isBefore(endTime)) {
            throw new IllegalArgumentException("시작 시간은 종료 시간보다 빨라야 합니다.");
        }

        if (startTime.isBefore(SEARCH_START_TIME) || endTime.isAfter(SEARCH_END_TIME)) {
            throw new IllegalArgumentException("공강 시간은 09:00부터 18:00 사이여야 합니다.");
        }
        //(...).compareTo(...) : 앞이더작으면 음수, 같으면 0, 앞이 더 크면 양수
        //즉 공간시간이 1시간보다 짧은지 판단하는조건
        if (Duration.between(startTime, endTime).compareTo(MINIMUM_DURATION) < 0) {
            throw new IllegalArgumentException("공강 시간은 최소 1시간 이상이어야 합니다.");
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
}
