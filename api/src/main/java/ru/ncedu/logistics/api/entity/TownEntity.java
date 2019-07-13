package ru.ncedu.logistics.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.validation.PatternObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
@NoArgsConstructor
@Document("towns")
public class TownEntity {
    @Id
    private String id;

    @NotBlank
    @Indexed
    private String name;

    @Valid
    private List<Road> roads;

    @Data
    @NoArgsConstructor
    public static class Road {
        @NotBlank
        @PatternObjectId
        private String townId;

        private double distance;
    }

    public TownEntity(TownDTO source) {
        this.id = source.getId();
        this.name = source.getName();
        this.roads = source.getRoads();
    }
}
