package ru.ncedu.logistics.dto;

import ru.ncedu.logistics.data.entity.TownEntity;

public class OfficeDTO {

    private Integer id;
    private Integer phone;
    private TownDTO town;

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

    public TownDTO getTown() {
        return town;
    }

    public void setTown(TownDTO town) {
        this.town = town;
    }
}
