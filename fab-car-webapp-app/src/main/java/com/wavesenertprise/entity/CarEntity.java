package com.wavesenertprise.entity;

import javax.persistence.*;

@Entity
public class CarEntity {

    @Id
    private String number;
    private String name;
    private String owner;

    public CarEntity(String number, String name, String owner) {
        this.number = number;
        this.name = name;
        this.owner = owner;
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

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
