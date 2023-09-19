package com.wavesenterprise.contract.domain;

public class Car {
    private String name;
    private String owner;
    private String number;

    public Car(String name, String owner, String number) {
        this.name = name;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
