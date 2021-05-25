package Beans;

public class Subjectofstudent {
    private String subjectID;
    private String subjectName;
    private int credit;
    private String room;
    private boolean isPaid;
    private float scoreMidTerm;
    private float scoreFinalTerm;
    private int teacherID;
    private String teacherName;

    public Subjectofstudent(String subjectID, String subjectName, int credit, String room, boolean isPaid, float scoreMidTerm, float scoreFinalTerm, int teacherID, String teacherName) {
        this.subjectID = subjectID;
        this.subjectName = subjectName;
        this.credit = credit;
        this.room = room;
        this.isPaid = isPaid;
        this.scoreMidTerm = scoreMidTerm;
        this.scoreFinalTerm = scoreFinalTerm;
        this.teacherID = teacherID;
        this.teacherName = teacherName;
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

    public int getCredit() {
        return credit;
    }

    public void setCredit(int credit) {
        this.credit = credit;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public boolean isPaid() {
        return isPaid;
    }

    public void setPaid(boolean paid) {
        isPaid = paid;
    }

    public float getScoreMidTerm() {
        return scoreMidTerm;
    }

    public void setScoreMidTerm(float scoreMidTerm) {
        this.scoreMidTerm = scoreMidTerm;
    }

    public float getScoreFinalTerm() {
        return scoreFinalTerm;
    }

    public void setScoreFinalTerm(float scoreFinalTerm) {
        this.scoreFinalTerm = scoreFinalTerm;
    }

    public int getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(int teacherID) {
        this.teacherID = teacherID;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
}
