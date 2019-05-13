package ru.ncedu.logistics.model.entity;

import java.io.Serializable;

public class OfferingId implements Serializable {
    private int officeId;
    private int productId;

    public Integer getOfficeId() {
        return officeId;
    }

    public void setOfficeId(Integer officeId) {
        this.officeId = officeId;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }
}
