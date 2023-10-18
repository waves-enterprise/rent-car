package com.wavesenertprise.entity;

import javax.persistence.*;

@Entity
public class CarEntity {

    @Id
    private String number;
    private String name;
    private String renter;

    public CarEntity(String number, String name, String renter) {
        this.number = number;
        this.name = name;
        this.renter = renter;
    }

    public CarEntity() {}

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRenter() {
        return renter;
    }

    public void setRenter(String owner) {
        this.renter = owner;
    }
}
