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
        OfferingEntity offeringEntity1 = offeringDAO.findById(offeringEntity.getOfferingId());
        return toOfferingDTO(offeringEntity1);
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

    public List<OfferingDTO> findByProductId(int productId){
        return offeringDAO.findByProductId(productId).stream().map(this::toOfferingDTO).collect(Collectors.toList());
    }

    public OfferingDTO findById(OfferingId offeringId){
        return toOfferingDTO(offeringDAO.findById(offeringId));
    }

    public OfferingDTO toOfferingDTO(OfferingEntity offeringEntity){
        OfferingDTO offeringDTO = new OfferingDTO();
        offeringDTO.setOffice(officeService.toOfficeDTO(offeringEntity.getOffice()));
        offeringDTO.setProduct(productService.toProductDTO(offeringEntity.getProduct()));
        offeringDTO.setPrice(offeringEntity.getPrice());
        return offeringDTO;
    }

    public OfferingEntity toOfferingEntity(OfferingDTO offeringDTO){
        OfferingEntity offeringEntity = new OfferingEntity();
        OfferingId offeringId = new OfferingId();
        offeringId.setOfficeId(offeringDTO.getOffice().getId());
        offeringId.setProductId(offeringDTO.getProduct().getId());
        offeringEntity.setOfferingId(offeringId);
        offeringEntity.setPrice(offeringDTO.getPrice());
        return offeringEntity;
    }
}
