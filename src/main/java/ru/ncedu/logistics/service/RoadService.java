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
        roadEntity = roadDAO.findById(roadEntity.getRoadId());
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
        roadEntity.setDistance(roadDTO.getDistance());
        roadEntity.setLeftTown(townService.toTownEntity(roadDTO.getLeftTown()));
        roadEntity.setRightTown(townService.toTownEntity(roadDTO.getRightTown()));
        return roadEntity;
    }
}
