package ru.ncedu.logistics.model.entity;

import java.io.Serializable;

public class RoadId implements Serializable {
    private int leftId;
    private int rightId;

    public Integer getLeftId() {
        return leftId;
    }

    public void setLeftId(Integer leftId) {
        this.leftId = leftId;
    }

    public Integer getRightId() {
        return rightId;
    }

    public void setRightId(Integer rightId) {
        this.rightId = rightId;
    }
}