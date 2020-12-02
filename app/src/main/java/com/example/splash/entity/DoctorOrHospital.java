package com.example.splash.entity;

import java.io.Serializable;

public class DoctorOrHospital implements Serializable {
    public String name;
    public String address;
    public String contact;
    public int rating;

    public DoctorOrHospital(String name, String address, String contact, int rating) {
        this.name = name;
        this.address = address;
        this.contact = contact;
        this.rating = rating;
    }
}
