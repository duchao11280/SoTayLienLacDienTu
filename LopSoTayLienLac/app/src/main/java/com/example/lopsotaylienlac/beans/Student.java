package com.example.lopsotaylienlac.beans;

import java.util.Date;

public class Student {
    private int studentID;
    private String password;
    private String studentName;
    private Date dob;
    private String phonenumber;
    private String address;
    private String classname;
    private int parentID;

    public Student(int studentID, String password, String studentName, Date dob, String phonenumber, String address, String classname, int parentID) {
        this.studentID = studentID;
        this.password = password;
        this.studentName = studentName;
        this.dob = dob;
        this.phonenumber = phonenumber;
        this.address = address;
        this.classname = classname;
        this.parentID = parentID;
    }

    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getClassname() {
        return classname;
    }

    public void setClassname(String classname) {
        this.classname = classname;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID=" + studentID +
                ", password='" + password + '\'' +
                ", studentName='" + studentName + '\'' +
                ", dob=" + dob +
                ", phonenumber='" + phonenumber + '\'' +
                ", address='" + address + '\'' +
                ", classname='" + classname + '\'' +
                ", parentID=" + parentID +
                '}';
    }
}
