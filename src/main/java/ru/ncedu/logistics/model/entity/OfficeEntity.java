package ru.ncedu.logistics.model.entity;

import java.io.Serializable;

public class OfficeEntity implements Serializable {
    private Integer id;
    private Integer phone;
    private Integer townId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public Integer getTownId() {
        return townId;
    }

    public void setTownId(Integer townId) {
        this.townId = townId;
    }
}
