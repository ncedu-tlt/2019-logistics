package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfferingEntity;
import ru.ncedu.logistics.data.entity.OfferingId;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OfferingDAO extends LogisticsDAO<OfferingEntity, OfferingId> {

    public OfferingDAO(){
        init(OfferingEntity.class);
    }

    public List<OfferingEntity> findByOfficeId(int officeId){
        return this.entityManager.createQuery("FROM " + OfferingEntity.class +
                                            " offering WHERE offering.offeringId.officeId = " + officeId).getResultList();
    }

    public List<OfferingEntity> findByProductId(int productId){
        return this.entityManager.createQuery("FROM " + OfferingEntity.class +
                " offering WHERE offering.offeringId.productId = " + productId).getResultList();
    }
}
