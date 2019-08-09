package ru.ncedu.logistics.api.service;

import org.springframework.data.domain.Pageable;
import ru.ncedu.logistics.api.dto.OfficeDTO;
import ru.ncedu.logistics.api.entity.OfficeEntity;

import java.util.List;

public interface OfficeService {
    OfficeDTO create(OfficeDTO office);
    OfficeDTO getById(String id);
    OfficeDTO update(String id, OfficeDTO office);
    void delete(String id);

    List<OfficeDTO> find(String nameRegex, Pageable pageable, List<String> fields);

    long count(String nameRegex);
    long countOfficesInTown(String name);

    OfficeDTO addOffering(String id, OfficeEntity.Offering offering);
    OfficeDTO updateOffering(String officeId, OfficeEntity.Offering offering);
    OfficeDTO removeOffering(String officeId, OfficeEntity.Offering offering);
}
