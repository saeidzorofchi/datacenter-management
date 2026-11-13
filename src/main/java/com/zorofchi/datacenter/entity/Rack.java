package com.zorofchi.datacenter.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Rack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;
    private int rackNumber;
    private int totalUnits;
    private String description;

    @ManyToOne
    private RackRow rackRow;

    @OneToMany(mappedBy = "rack")
    private List<Equipment> equipments;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRackNumber() {
        return rackNumber;
    }

    public void setRackNumber(int rackNumber) {
        this.rackNumber = rackNumber;
    }

    public int getTotalUnits() {
        return totalUnits;
    }

    public void setTotalUnits(int totalUnits) {
        this.totalUnits = totalUnits;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RackRow getRackRow() {
        return rackRow;
    }

    public void setRackRow(RackRow rackRow) {
        this.rackRow = rackRow;
    }
}
