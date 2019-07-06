package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfficeEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class OfficeDAO extends LogisticsDAO<OfficeEntity, Integer> {

    private final static String FIND_BY_TOWN_ID =
            "FROM " + OfficeEntity.class.getName() + " office WHERE office.town.id = :townId";

    private final static String FIND_BY_TOWN_AND_PHONE =
            "FROM " + OfficeEntity.class.getName() + " office WHERE office.town.id = :townId and office.phone = :phone";

    private final static String EXIST_BY_TOWN_AND_PHONE =
            "SELECT COUNT(office) FROM " + OfficeEntity.class.getName() +
                    " office WHERE office.town.id = :townId and office.phone = :phone";

    private final static String EXIST_BY_ID =
            "SELECT COUNT(office) FROM " + OfficeEntity.class.getName() + " office WHERE office.id = :officeId";


    public OfficeDAO(){
        init(OfficeEntity.class);
    }

    public List<OfficeEntity> findByTownId(int townId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);

        return this.customFindListQuery(FIND_BY_TOWN_ID, args);
    }

    public OfficeEntity findByTownAndPhone(int townId, int phone){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);
        args.put("phone", phone);

        return this.customFindSingleQuery(FIND_BY_TOWN_AND_PHONE, args);
    }

    public boolean existsByTownAndPhone(int townId, int phone){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);
        args.put("phone", phone);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_BY_TOWN_AND_PHONE);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();
        em.close();
        return result > 0;
    }

    public boolean existsById(int id){
        Map<String, Object> args = new HashMap<>();
        args.put("officeId", id);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_BY_ID);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();
        em.close();
        return result > 0;
    }
}
