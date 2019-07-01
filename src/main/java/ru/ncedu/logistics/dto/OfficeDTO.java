package ru.ncedu.logistics.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.ncedu.logistics.service.JsonViews;

public class OfficeDTO {

    @JsonView(JsonViews.Flat.class)
    private Integer id;

    @JsonView(JsonViews.Flat.class)
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
