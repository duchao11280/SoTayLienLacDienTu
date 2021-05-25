package Beans;

import java.util.Date;

public class Parent {
    private int parentID;
    private String password;
    private String parentName;
    private Date dob;
    private String phonenumber;
    private String address;

    public Parent(int parentID, String password, String parentName, Date dob, String phonenumber, String address) {
        this.parentID = parentID;
        this.password = password;
        this.parentName = parentName;
        this.dob = dob;
        this.phonenumber = phonenumber;
        this.address = address;
    }

    public int getParentID() {
        return parentID;
    }

    public void setParentID(int parentID) {
        this.parentID = parentID;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
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
}
