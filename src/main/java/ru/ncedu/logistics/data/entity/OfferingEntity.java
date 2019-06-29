package ru.ncedu.logistics.data.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "offerings")
public class OfferingEntity implements Serializable {

    @EmbeddedId
    private OfferingId offeringId;

    @Column(name = "price")
    private double price;

    @ManyToOne
    @JoinColumn(name = "office_id", insertable = false, updatable = false)
    private OfficeEntity officeEntity;

    @ManyToOne
    @JoinColumn(name = "product_id", insertable = false, updatable = false)
    private ProductEntity productEntity;

    public OfferingId getOfferingId() {
        return offeringId;
    }

    public void setOfferingId(OfferingId offeringId) {
        this.offeringId = offeringId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public OfficeEntity getOfficeEntity() {
        return officeEntity;
    }

    public ProductEntity getProductEntity() {
        return productEntity;
    }
}
