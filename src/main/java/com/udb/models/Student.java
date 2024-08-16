package com.udb.models;

public class Student {
    private String carnet;
    private String name;

    public Student(String carnet, String name) {
        this.carnet = carnet;
        this.name = name;
    }

    public String getCarnet() {
        return carnet;
    }

    public String getName() {
        return name;
    }
}
