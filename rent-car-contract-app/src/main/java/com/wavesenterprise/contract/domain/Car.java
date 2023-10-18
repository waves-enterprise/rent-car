package com.wavesenterprise.contract.domain;

public class Car {
    private String name;
    private String renter;
    private String number;

    public Car(String name, String renter, String number) {
        this.name = name;
        this.renter = renter;
        this.number = number;
    }

    public Car() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public String getRenter() {
        return renter;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRenter(String renter) {
        this.renter = renter;
    }
}
