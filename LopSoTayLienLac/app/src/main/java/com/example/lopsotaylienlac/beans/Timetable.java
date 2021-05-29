package com.example.lopsotaylienlac.beans;

import java.util.Date;

public class Timetable {
    private int timetableID;
    private String subjectID;
    private Date timeStart;
    private Date timeEnd;
    private Date date;
    private boolean isOff;

    public Timetable(int timetableID, String subjectID, Date timeStart, Date timeEnd, Date date, boolean isOff) {
        this.timetableID = timetableID;
        this.subjectID = subjectID;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.date = date;
        this.isOff = isOff;
    }

    public int getTimetableID() {
        return timetableID;
    }

    public void setTimetableID(int timetableID) {
        this.timetableID = timetableID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
    }

    public Date getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(Date timeStart) {
        this.timeStart = timeStart;
    }

    public Date getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(Date timeEnd) {
        this.timeEnd = timeEnd;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public boolean isOff() {
        return isOff;
    }

    public void setOff(boolean off) {
        isOff = off;
    }

    @Override
    public String toString() {
        return "Timetable{" +
                "timetableID=" + timetableID +
                ", subjectID='" + subjectID + '\'' +
                ", timeStart=" + timeStart +
                ", timeEnd=" + timeEnd +
                ", date=" + date +
                ", isOff=" + isOff +
                '}';
    }
}
