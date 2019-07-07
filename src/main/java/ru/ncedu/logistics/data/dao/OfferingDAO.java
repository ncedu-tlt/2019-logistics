package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfferingEntity;
import ru.ncedu.logistics.data.entity.OfferingId;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class OfferingDAO extends LogisticsDAO<OfferingEntity, OfferingId> {

    private final static String FIND_BY_OFFICE_ID =
            "FROM " + OfferingEntity.class.getName() + " offering WHERE offering.office.id = :officeId";

    private final static String FIND_BY_PRODUCT_ID =
            "FROM " + OfferingEntity.class.getName() + " offering WHERE offering.product.id = :productId";

    private final static String EXIST_MIN_OFFER =
            "SELECT COUNT(offer) FROM " + OfferingEntity.class.getName() +
            " offer WHERE offer.office.town.id = :townId AND offer.product.id = :productId";

    private final static String FIND_MIN_OFFER =
            "SELECT offering FROM " + OfferingEntity.class.getName() +
            " offering WHERE offering.office.town.id = :townId AND offering.product.id = :productId" +
            " ORDER BY offering.price ASC";

    private final static String EXIST_BY_ID =
            "SELECT COUNT(offering) FROM " + OfferingEntity.class.getName() +
                    " offering WHERE offering.product.id = :productId and offering.office.id = :officeId";

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

    public OfferingEntity findMinOffer(int townId, int productId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);
        args.put("productId", productId);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(FIND_MIN_OFFER);
        args.forEach(query::setParameter);

        return (OfferingEntity) query.setMaxResults(1).getSingleResult();
    }

    public boolean existsMinOffer(int townId, int productId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);
        args.put("productId", productId);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_MIN_OFFER);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();

        return result > 0;
    }

    public boolean existsById(int productId, int officeId){
        Map<String, Object> args = new HashMap<>();
        args.put("productId", productId);
        args.put("officeId", officeId);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_BY_ID);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();
        em.close();
        return result > 0;
    }
}
