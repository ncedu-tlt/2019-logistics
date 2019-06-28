package ru.ncedu.logistics.service;

import ru.ncedu.logistics.data.dao.OfferingDAO;
import ru.ncedu.logistics.data.entity.OfferingEntity;
import ru.ncedu.logistics.data.entity.OfferingId;
import ru.ncedu.logistics.dto.OfferingDTO;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class OfferingService {

    @Inject
    private OfferingDAO offeringDAO;
    @Inject
    private OfficeService officeService;
    @Inject
    private ProductService productService;

    public OfferingDTO create(OfferingDTO offeringDTO){
        OfferingEntity offeringEntity = toOfferingEntity(offeringDTO);
        offeringDAO.create(offeringEntity);
        return toOfferingDTO(offeringEntity);
    }

    public OfferingDTO update(OfferingDTO offeringDTO){
        OfferingEntity offeringEntity = toOfferingEntity(offeringDTO);
        offeringDAO.update(offeringEntity);
        return toOfferingDTO(offeringEntity);
    }

    public void delete(OfferingDTO offeringDTO){
        OfferingEntity offeringEntity = toOfferingEntity(offeringDTO);
        offeringDAO.delete(offeringEntity);
    }

    public List<OfferingDTO> findByOfficeId(int officeId){
        return offeringDAO.findByOfficeId(officeId).stream().map(this::toOfferingDTO).collect(Collectors.toList());
    }

    public OfferingDTO toOfferingDTO(OfferingEntity offeringEntity){
        OfferingDTO offeringDTO = new OfferingDTO();
        offeringDTO.setOfficeDTO(officeService.toOfficeDTO(offeringEntity.getOfficeEntity()));
        offeringDTO.setProductDTO(productService.toProductDTO(offeringEntity.getProductEntity()));
        offeringDTO.setPrice(offeringEntity.getPrice());
        return offeringDTO;
    }

    public OfferingEntity toOfferingEntity(OfferingDTO offeringDTO){
        OfferingEntity offeringEntity = new OfferingEntity();
        OfferingId offeringId = new OfferingId();
        offeringId.setOfficeId(offeringDTO.getOfficeDTO().getId());
        offeringId.setProductId(offeringDTO.getProductDTO().getId());
        offeringEntity.setOfferingId(offeringId);
        offeringEntity.setPrice(offeringDTO.getPrice());
        return offeringEntity;
    }
}
