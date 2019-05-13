package ru.ncedu.logistics.model.entity;

import java.io.Serializable;

public class RoadEntity implements Serializable {
    private RoadId id;
    private double distance;

    public RoadId getId() {
        return id;
    }

    public void setId(RoadId id) {
        this.id = id;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
