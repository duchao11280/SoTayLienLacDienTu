package com.example.lopsotaylienlac.beans;

public class Subjectclass {
    private String subjectID;
    private String subjectName;
    private String room;
    private int credit;
    private int teacherID;

    public Subjectclass(String subjectID, String subjectName, String room, int credit, int teacherID) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.room = room;
        this.credit = credit;
        this.teacherID = teacherID;
    }

    public String getSubjectID() {
        return subjectID;
    }

    public void setSubjectID(String subjectID) {
        this.subjectID = subjectID;
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

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }
}
