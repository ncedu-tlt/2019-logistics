package ru.ncedu.logistics.data.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class RoadId implements Serializable {

    @Column(name = "left_town_id")
    private int leftId;

    @Column(name = "right_town_id")
    private int rightId;

    public int getLeftId() {
        return leftId;
    }

    public void setLeftId(int leftId) {
        this.leftId = leftId;
    }

    public int getRightId() {
        return rightId;
    }

    public void setRightId(int rightId) {
        this.rightId = rightId;
    }
}
