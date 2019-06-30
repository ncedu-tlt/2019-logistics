package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.RoadEntity;
import ru.ncedu.logistics.data.entity.RoadId;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class RoadDAO extends LogisticsDAO<RoadEntity, RoadId> {

    private static final String FIND_BY_TOWN_ID =
            "FROM " + RoadEntity.class.getName() + " road WHERE road.roadId.leftId LIKE :townId OR road.roadId.rightId LIKE :townId";

    public RoadDAO(){
        init(RoadEntity.class);
    }

    public List<RoadEntity> findByTownId(int townId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);

        return this.customFindListQuery(FIND_BY_TOWN_ID, args);
    }
}
