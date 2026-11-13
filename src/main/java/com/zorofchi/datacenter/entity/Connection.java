package com.zorofchi.datacenter.entity;

import jakarta.persistence.*;

@Entity
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private Port sourcePort;

    @ManyToOne
    private Port destinationPort;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Port getSourcePort() {
        return sourcePort;
    }

    public void setSourcePort(Port sourcePort) {
        this.sourcePort = sourcePort;
    }

    public Port getDestinationPort() {
        return destinationPort;
    }

    public void setDestinationPort(Port destinationPort) {
        this.destinationPort = destinationPort;
    }
}
