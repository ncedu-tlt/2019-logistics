package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.TownEntity;

import javax.ejb.Stateless;

@Stateless
public class TownDAO extends LogisticsDAO<TownEntity, Integer> {

    public TownDAO() {
        init(TownEntity.class);
    }

}
