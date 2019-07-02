package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfferingEntity;
import ru.ncedu.logistics.data.entity.OfferingId;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class OfferingDAO extends LogisticsDAO<OfferingEntity, OfferingId> {

    private final static String FIND_BY_OFFICE_ID =
            "FROM " + OfferingEntity.class.getName() + " offering WHERE offering.office.id = :officeId";

    private final static String FIND_BY_PRODUCT_ID =
            "FROM " + OfferingEntity.class.getName() + " offering WHERE offering.product.id = :productId";

    public OfferingDAO(){
        init(OfferingEntity.class);
    }

    public List<OfferingEntity> findByOfficeId(int officeId){
        Map<String, Object> args = new HashMap<>();
        args.put("officeId", officeId);

        return this.customFindListQuery(FIND_BY_OFFICE_ID, args);
    }

    public List<OfferingEntity> findByProductId(int productId){
        Map<String, Object> args = new HashMap<>();
        args.put("productId", productId);

        return this.customFindListQuery(FIND_BY_PRODUCT_ID, args);
    }
}
