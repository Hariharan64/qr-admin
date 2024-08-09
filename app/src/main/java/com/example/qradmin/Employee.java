package com.example.qradmin;

public class Employee {
    // Employee class to hold employee data

    public String name;
    public String number;
    public String id;
    public String phone;

    public Employee() {
        // Default constructor required for calls to DataSnapshot.getValue(Employee.class)
    }

    public Employee(String name, String number, String id, String phone) {
        this.name = name;
        this.number = number;
        this.id = id;
        this.phone = phone;
    }
}