package ru.ncedu.logistics.api.service;

import org.springframework.data.domain.Pageable;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.entity.TownEntity;
import ru.ncedu.logistics.api.exception.TownNotFoundException;

import java.util.List;

public interface TownService {
    TownDTO create(TownDTO town);
    TownDTO update(String id, TownDTO town) throws TownNotFoundException;
    TownDTO getById(String id) throws TownNotFoundException;
    TownDTO getByName(String name) throws TownNotFoundException;
    void delete(String id);

    List<TownDTO> find(String nameRegex, Pageable pageable, List<String> fields);

    long count(String nameRegex);

    TownDTO addRoad(String townId, TownEntity.Road road);
    TownDTO updateRoad(String townId, TownEntity.Road road);
    TownDTO removeRoad(String townId, TownEntity.Road road);
}
