package com.example.lopsotaylienlac.beans;

import java.util.Date;

public class Fee {
    private int feeID;
    private int year;
    private int money;
    private Date dateUpload;

    public Fee(int feeID, int year, int money, Date dateUpload) {
        this.feeID = feeID;
        this.year = year;
        this.money = money;
        this.dateUpload = dateUpload;
    }

    public int getFeeID() {
        return feeID;
    }

    public void setFeeID(int feeID) {
        this.feeID = feeID;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public Date getDateUpload() {
        return dateUpload;
    }

    public void setDateUpload(Date dateUpload) {
        this.dateUpload = dateUpload;
    }

    @Override
    public String toString() {
        return "Fee{" +
                "feeID=" + feeID +
                ", year=" + year +
                ", money=" + money +
                ", dateUpload=" + dateUpload +
                '}';
    }
}
