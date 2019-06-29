package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.OfficeDAO;
import ru.ncedu.logistics.data.entity.OfficeEntity;
import ru.ncedu.logistics.dto.OfficeDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OfficeService {

    @Inject
    private OfficeDAO officeDAO;
    @Inject
    private TownService townService;

    public OfficeDTO create(OfficeDTO officeDTO){
        OfficeEntity officeEntity = toOfficeEntity(officeDTO);
        officeDAO.create(officeEntity);
        return toOfficeDTO(officeEntity);
    }

    public OfficeDTO update(OfficeDTO officeDTO){
        OfficeEntity officeEntity = toOfficeEntity(officeDTO);
        officeDAO.update(officeEntity);
        return toOfficeDTO(officeEntity);
    }

    public void delete(OfficeDTO officeDTO){
        OfficeEntity officeEntity = toOfficeEntity(officeDTO);
        officeDAO.delete(officeEntity);
    }

    public void deleteById(int officeId){
        officeDAO.deleteById(officeId);
    }

    public List<OfficeDTO> findByTownId(int townId){
        return officeDAO.findByTownId(townId).stream().map(this::toOfficeDTO).collect(Collectors.toList());
    }

    public OfficeDTO findById(int officeId){
        return toOfficeDTO(officeDAO.findById(officeId));
    }

    public OfficeDTO toOfficeDTO(OfficeEntity officeEntity){
        OfficeDTO officeDTO = new OfficeDTO();
        officeDTO.setId(officeEntity.getId());
        officeDTO.setPhone(officeEntity.getPhone());
        officeDTO.setTown(townService.toTownDTO(officeEntity.getTown()));
        return officeDTO;
    }

    public OfficeEntity toOfficeEntity(OfficeDTO officeDTO){
        OfficeEntity officeEntity = new OfficeEntity();
        officeEntity.setId(officeDTO.getId());
        officeEntity.setPhone(officeDTO.getPhone());
        officeEntity.setTown(townService.toTownEntity(officeDTO.getTown()));
        return officeEntity;
    }

}
