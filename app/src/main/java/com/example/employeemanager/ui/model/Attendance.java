package com.example.employeemanager.ui.model;

public class Attendance {
    String date;
    String pay;
    //add constructor with parameters
    public Attendance(String date, String pay){
        this.date = date;
        this.pay = pay;
    }
    //Generate getters and setters
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }
}
