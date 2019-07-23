package ru.ncedu.logistics.api.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import ru.ncedu.logistics.api.dto.OfficeDTO;
import ru.ncedu.logistics.api.validation.PatternObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Objects;
import java.util.Set;

@Data
@NoArgsConstructor
@CompoundIndex(def = "{'name':1, 'phone':1, 'townId':1}")
@Document("offices")
public class OfficeEntity {
    @Id
    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @Valid
    private Set<Offering> offerings;

    @NotBlank
    @PatternObjectId
    private String townId;

    @Data
    @NoArgsConstructor
    public static class Offering{
        @NotBlank
        @Indexed
        private String productName;

        private double price;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Offering offering = (Offering) o;
            return productName.equals(offering.productName);
        }

        @Override
        public int hashCode() {
            return Objects.hash(productName);
        }
    }

    public OfficeEntity(OfficeDTO source){
        this.id = source.getId();
        this.name = source.getName();
        this.phone = source.getPhone();
        this.offerings = source.getOfferings();
        this.townId = source.getTownId();
    }
}
