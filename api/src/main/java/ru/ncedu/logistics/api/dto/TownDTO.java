package ru.ncedu.logistics.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ncedu.logistics.api.entity.TownEntity;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
public class TownDTO {

    private String id;

    @NotBlank
    private String name;

    @Valid
    private List<TownEntity.Road> roads;

    public TownDTO(TownEntity source) {
        this.id = source.getId();
        this.name = source.getName();
        this.roads = source.getRoads();
    }

}
