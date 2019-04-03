package ru.ncedu.logistics;

import java.util.Objects;

public class Road {
    private Town first;
    private Town second;
    private double distance; //Kilometres

    private Road(Town first, Town second, double distanceKm) {
        if (first.equals(second)) {
            throw new IllegalArgumentException("Can't create road from town to same town");
        }

        this.first = first;
        this.second = second;
        this.distance = distanceKm;
    }

    public Town getFirst() {
        return first;
    }

    public void setFirst(Town first) {
        this.first = first;
    }

    public Town getSecond() {
        return second;
    }

    public void setSecond(Town second) {
        this.second = second;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public static Road fromKilometers(Town first, Town second, double distanceKilometers) {
        return new Road(first, second, distanceKilometers);
    }

    public static Road fromMeters(Town first, Town second, double distanceMeters) {
        return new Road(first, second, distanceMeters/1000);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Road road = (Road) o;
        return Double.compare(road.getDistance(), getDistance()) == 0 &&
                getFirst().equals(road.getFirst()) &&
                getSecond().equals(road.getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirst(), getSecond(), getDistance());
    }
}