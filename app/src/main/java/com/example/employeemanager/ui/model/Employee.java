package com.example.employeemanager.ui.model;

import java.io.Serializable;

//Make class Serializable for passing object of this class via intent using bundle
public class Employee implements Serializable {
    int _id;
    String NIC;
    String joiningDate;
    String fullName;
    String Designation;
    String MobileNo;
    String BasicPay;
    String Address;
    String Details;

    //add default constructor
    public Employee(){   }

    //add constructor with parameters and ID
    public Employee(int id, String NIC, String joiningDate,String fullName, String Designation
    ,String MobileNo, String BasicPay,String Address, String Details){
        this._id = id;
        this.NIC = NIC;
        this.joiningDate = joiningDate;
        this.fullName = fullName;
        this.Designation = Designation;
        this.MobileNo = MobileNo;
        this.BasicPay = BasicPay;
        this.Address = Address;
        this.Details = Details;
    }
    //add constructor with parameters without ID
    public Employee(String NIC, String joiningDate,String fullName, String Designation
            ,String MobileNo, String BasicPay,String Address, String Details){
        this.NIC = NIC;
        this.joiningDate = joiningDate;
        this.fullName = fullName;
        this.Designation = Designation;
        this.MobileNo = MobileNo;
        this.BasicPay = BasicPay;
        this.Address = Address;
        this.Details = Details;
    }
    //Generate getters and setters

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String getNIC() {
        return NIC;
    }

    public void setNIC(String NIC) {
        this.NIC = NIC;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String designation) {
        Designation = designation;
    }

    public String getMobileNo() {
        return MobileNo;
    }

    public void setMobileNo(String mobileNo) {
        MobileNo = mobileNo;
    }

    public String getBasicPay() {
        return BasicPay;
    }

    public void setBasicPay(String basicPay) {
        BasicPay = basicPay;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDetails() {
        return Details;
    }

    public void setDetails(String details) {
        Details = details;
    }
}
