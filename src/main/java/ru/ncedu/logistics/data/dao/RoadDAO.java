package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.RoadEntity;
import ru.ncedu.logistics.data.entity.RoadId;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class RoadDAO extends LogisticsDAO<RoadEntity, RoadId> {

    private static final String FIND_BY_TOWN_ID =
            "FROM " + RoadEntity.class.getName() + " road WHERE road.roadId.leftId = :townId OR road.roadId.rightId = :townId";

    private static final String EXISTS_BY_ID =
            "SELECT COUNT(road) FROM " + RoadEntity.class.getName() + " road WHERE road.roadId.leftId = :left AND road.roadId.rightId = :right";

    private static final String EXISTS_BY_TOWN_ID =
            "SELECT COUNT(road) FROM " + RoadEntity.class.getName() + " road WHERE road.roadId.leftId = :id OR road.roadId.rightId = :id";

    public RoadDAO(){
        init(RoadEntity.class);
    }

    public List<RoadEntity> findByTownId(int townId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);

        return this.customFindListQuery(FIND_BY_TOWN_ID, args);
    }

    public boolean existsById(RoadId roadId){
        Map<String, Object> args = new HashMap<>();
        args.put("left", roadId.getLeftId());
        args.put("right", roadId.getRightId());

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXISTS_BY_ID);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();

        return result > 0;
    }

    public boolean existsByTownId(int id){
        Map<String, Object> args = new HashMap<>();
        args.put("id", id);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXISTS_BY_TOWN_ID);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();

        return result > 0;
    }
}
