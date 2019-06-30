package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.TownEntity;

import javax.ejb.Stateless;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class TownDAO extends LogisticsDAO<TownEntity, Integer> {

    private static final String FIND_BY_NAME = "FROM " + TownEntity.class.getName() + "town WHERE obj.name = :name";

    public TownDAO() {
        init(TownEntity.class);
    }

    public TownEntity findByName(String name){
        Map<String, Object> args = new HashMap<>();
        args.put("name", name);

        return this.customFindSingleQuery(FIND_BY_NAME, args);
    }

}
