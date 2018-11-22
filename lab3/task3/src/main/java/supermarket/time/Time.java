package com.company.supermarket.time;

public class Time {
    private int minute;
    private int hour;

    public Time(int minute, int hour) {
        this.minute = initializeMinute(minute);
        this.hour = initializeHour(hour);
    }

    private int initializeMinute(int minute) {
        if (minute >= 60) {
            return 59;
        }
        if (minute < 0) {
            return 0;
        }
        return minute;
    }

    private int initializeHour(int hour) {
        if (hour > 24) {
            return 23;
        }
        if (hour < 0) {
            return 0;
        }
        return hour;
    }

    public String getCurrentTime() {
        return String.valueOf(hour) + ":" + String.valueOf(minute);
    }

    public void updateTime(int step) {
        minute += step;
        if (minute >= 60) {
            minute = 0;
            hour++;
        }
        if (hour >= 24) {
            hour = 0;
        }
    }
}
