package com.zorofchi.datacenter.entity;

import jakarta.persistence.*;

@Entity
public class Port {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int portNumber;
    @Enumerated(EnumType.STRING)
    private PortSide side;


    @ManyToOne
    private Equipment equipment;

    public PortSide getSide() {
        return side;
    }

    public void setSide(PortSide side) {
        this.side = side;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getPortNumber() {
        return portNumber;
    }

    public void setPortNumber(int portNumber) {
        this.portNumber = portNumber;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }
}
