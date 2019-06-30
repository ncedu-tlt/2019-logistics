package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfficeEntity;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Stateless
public class OfficeDAO extends LogisticsDAO<OfficeEntity, Integer> {

    private final static String FIND_BY_TOWN_ID =
            "FROM " + OfficeEntity.class.getName() + " office WHERE office.town.id = :townId";

    public OfficeDAO(){
        init(OfficeEntity.class);
    }

    public List<OfficeEntity> findByTownId(int townId){
        Map<String, Object> args = new HashMap<>();
        args.put("townId", townId);

        return this.customFindListQuery(FIND_BY_TOWN_ID, args);
    }
}
