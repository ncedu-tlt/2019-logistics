package ru.ncedu.logistics.api.service;

import org.springframework.data.domain.Pageable;
import ru.ncedu.logistics.api.dto.TownDTO;
import ru.ncedu.logistics.api.exception.TownNotFoundException;

import java.util.List;

public interface TownService {
    TownDTO create(TownDTO town);
    TownDTO update(String id, TownDTO town) throws TownNotFoundException;
    void delete(String id);

    List<TownDTO> find(String nameRegex, Pageable pageable, List<String> fields);
    long count(String nameRegex);

    TownDTO getById(String id) throws TownNotFoundException;
}
