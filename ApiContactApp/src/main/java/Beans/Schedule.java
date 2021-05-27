package Beans;

import java.util.Date;

public class Schedule {
    private int timetableID;
    private String subjectID;
    private Date date;
    private String tmStart,tmEnd,subjectName,room;
    private int credit;
    private String teachername;
    private int tietbd,tietkt;

    public Schedule(int timetableID, String subjectID, Date date, String tmStart, String tmEnd, String subjectName, String room, int credit, String teachername, int tietbd, int tietkt) {
        this.timetableID = timetableID;
        this.subjectID = subjectID;
        this.date = date;
        this.tmStart = tmStart;
        this.tmEnd = tmEnd;
        this.subjectName = subjectName;
        this.room = room;
        this.credit = credit;
        this.teachername = teachername;
        this.tietbd = tietbd;
        this.tietkt = tietkt;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getTmStart() {
        return tmStart;
    }

    public void setTmStart(String tmStart) {
        this.tmStart = tmStart;
    }

    public String getTmEnd() {
        return tmEnd;
    }

    public void setTmEnd(String tmEnd) {
        this.tmEnd = tmEnd;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getTeachername() {
        return teachername;
    }

    public void setTeachername(String teachername) {
        this.teachername = teachername;
    }

    public int getTietbd() {
        return tietbd;
    }

    public void setTietbd(int tietbd) {
        this.tietbd = tietbd;
    }

    public int getTietkt() {
        return tietkt;
    }

    public void setTietkt(int tietkt) {
        this.tietkt = tietkt;
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "timetableID=" + timetableID +
                ", subjectID='" + subjectID + '\'' +
                ", date=" + date +
                ", tmStart='" + tmStart + '\'' +
                ", tmEnd='" + tmEnd + '\'' +
                ", subjectName='" + subjectName + '\'' +
                ", room='" + room + '\'' +
                ", credit=" + credit +
                ", teachername='" + teachername + '\'' +
                ", tietbd=" + tietbd +
                ", tietkt=" + tietkt +
                '}';
    }
}
