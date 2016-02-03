package com.example.exploreit.codes;

/**
 * Created by Cain on 03-02-2016.
 */
public class Event {
    private String typeofevent;
    private int fee;

    public String getTypeofevent() {
        return typeofevent;
    }

    public void setTypeofevent(String typeofevent) {
        this.typeofevent = typeofevent;
    }

    public int getFee() {
        return fee;
    }

    public void setFee(int fee) {
        this.fee = fee;
    }

    public String getDayofweek() {
        return dayofweek;
    }

    public void setDayofweek(String dayofweek) {
        this.dayofweek = dayofweek;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    private String dayofweek;
    private int day,month,year;
}
