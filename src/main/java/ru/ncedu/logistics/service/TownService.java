package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.TownDAO;
import ru.ncedu.logistics.data.entity.TownEntity;
import ru.ncedu.logistics.dto.TownDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class TownService {

    @Inject
    private TownDAO townDAO;

    public TownDTO create(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townDAO.create(townEntity);
        return toTownDTO(townEntity);
    }

    public TownDTO update(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townEntity = townDAO.update(townEntity);
        return toTownDTO(townEntity);
    }

    public void delete(TownDTO town) {
        TownEntity townEntity = toTownEntity(town);
        townDAO.delete(townEntity);
    }

    public void deleteById(int townId){
        townDAO.deleteById(townId);
    }

    public List<TownDTO> findAll() {
        return townDAO.findAll().stream().map(this::toTownDTO).collect(Collectors.toList());
    }

    public TownDTO findById(int townId){
        return toTownDTO(townDAO.findById(townId));
    }

    public TownDTO findByName(String name){
        return toTownDTO(townDAO.findByName(name));
    }

    public TownDTO toTownDTO(TownEntity townEntity) {
        TownDTO townDTO = new TownDTO();
        townDTO.setId(townEntity.getId());
        townDTO.setName(townEntity.getName());
        return townDTO;
    }

    public TownEntity toTownEntity(TownDTO townDTO) {
        TownEntity townEntity = new TownEntity();
        townEntity.setId(townDTO.getId());
        townEntity.setName(townDTO.getName());
        return townEntity;
    }

}
