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
import java.util.Objects;
import java.util.Set;

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
    private Set<Road> roads;

    @Data
    @NoArgsConstructor
    public static class Road {
        @NotBlank
        @PatternObjectId
        private String townId;

        private double distance;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Road road = (Road) o;
            return townId.equals(road.townId);
        }

        @Override
        public int hashCode() {
            return Objects.hash(townId);
        }
    }

    public TownEntity(TownDTO source) {
        this.id = source.getId();
        this.name = source.getName();
        this.roads = source.getRoads();
    }
}
