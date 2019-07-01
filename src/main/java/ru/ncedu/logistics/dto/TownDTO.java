package ru.ncedu.logistics.dto;

import com.fasterxml.jackson.annotation.JsonView;
import ru.ncedu.logistics.service.JsonViews;

public class TownDTO {

    @JsonView(JsonViews.Flat.class)
    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
