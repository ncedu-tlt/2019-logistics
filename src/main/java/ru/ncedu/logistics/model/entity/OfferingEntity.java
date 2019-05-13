package ru.ncedu.logistics.model.entity;

import java.io.Serializable;

public class OfferingEntity implements Serializable {
    private OfferingId id;
    private double price;

    public OfferingId getId() {
        return id;
    }

    public void setId(OfferingId id) {
        this.id = id;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
