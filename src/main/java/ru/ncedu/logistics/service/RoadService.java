package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.RoadDAO;
import ru.ncedu.logistics.data.entity.RoadEntity;
import ru.ncedu.logistics.data.entity.RoadId;
import ru.ncedu.logistics.dto.RoadDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class RoadService {

    @Inject
    private RoadDAO roadDAO;

    @Inject
    private TownService townService;

    public RoadDTO create(RoadDTO roadDTO){
        RoadEntity roadEntity = toRoadEntity(roadDTO);
        roadDAO.create(roadEntity);
        return toRoadDTO(roadEntity);
    }

    public RoadDTO update(RoadDTO roadDTO){
        RoadEntity roadEntity = toRoadEntity(roadDTO);
        roadDAO.update(roadEntity);
        return toRoadDTO(roadEntity);
    }

    public void delete(RoadDTO roadDTO){
        RoadEntity roadEntity = toRoadEntity(roadDTO);
        roadDAO.delete(roadEntity);
    }

    public List<RoadDTO> findByTownId(int townId){
        return roadDAO.findByTownId(townId).stream().map(this::toRoadDTO).collect(Collectors.toList());
    }

    public RoadDTO toRoadDTO(RoadEntity roadEntity){
        RoadDTO roadDTO = new RoadDTO();
        roadDTO.setLeftTown(townService.toTownDTO(roadEntity.getLeftTown()));
        roadDTO.setRightTown(townService.toTownDTO(roadEntity.getRightTown()));
        roadDTO.setDistance(roadEntity.getDistance());
        return roadDTO;
    }

    public RoadEntity toRoadEntity(RoadDTO roadDTO){
        RoadEntity roadEntity = new RoadEntity();
        RoadId roadId = new RoadId();
        roadId.setLeftId(roadDTO.getLeftTown().getId());
        roadId.setRightId(roadDTO.getRightTown().getId());
        roadEntity.setRoadId(roadId);
        roadEntity.setDistance(roadDTO.getDistance());
        return roadEntity;
    }
}
