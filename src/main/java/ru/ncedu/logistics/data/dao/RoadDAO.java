package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.RoadEntity;
import ru.ncedu.logistics.data.entity.RoadId;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class RoadDAO extends LogisticsDAO<RoadEntity, RoadId> {

    public RoadDAO(){
        init(RoadEntity.class);
    }

    public List<RoadEntity> findByTownId(int townId){
        return this.entityManager.createQuery("FROM " + RoadEntity.class.getName() +
                " road WHERE road.roadId.leftId LIKE :townId OR road.roadId.rightId LIKE :townId")
                .setParameter("townId", townId)
                .getResultList();
    }
}
