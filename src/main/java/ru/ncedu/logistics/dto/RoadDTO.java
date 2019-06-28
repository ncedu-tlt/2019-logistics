package ru.ncedu.logistics.dto;

public class RoadDTO {

    private TownDTO leftTown;
    private TownDTO rightTown;
    private double distance;

    public TownDTO getLeftTown() {
        return leftTown;
    }

    public void setLeftTown(TownDTO leftTown) {
        this.leftTown = leftTown;
    }

    public TownDTO getRightTown() {
        return rightTown;
    }

    public void setRightTown(TownDTO rightTown) {
        this.rightTown = rightTown;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }
}
