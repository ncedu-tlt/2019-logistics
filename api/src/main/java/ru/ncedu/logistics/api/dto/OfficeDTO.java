package ru.ncedu.logistics.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import ru.ncedu.logistics.api.entity.OfficeEntity;
import ru.ncedu.logistics.api.validation.PatternObjectId;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
@NoArgsConstructor
public class OfficeDTO {

    private String id;

    @NotBlank
    private String name;

    @NotBlank
    private String phone;

    @NotBlank
    @PatternObjectId
    private String townId;

    @Valid
    private Set<OfficeEntity.Offering> offerings;

    public OfficeDTO(OfficeEntity source){
        this.id = source.getId();
        this.name = source.getName();
        this.phone = source.getPhone();
        this.offerings = source.getOfferings();
        this.townId = source.getTownId();
    }
}
