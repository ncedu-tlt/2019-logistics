package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.OfficeEntity;

import javax.ejb.Stateless;
import java.util.List;

@Stateless
public class OfficeDAO extends LogisticsDAO<OfficeEntity, Integer> {

    public OfficeDAO(){
        init(OfficeEntity.class);
    }

    public List<OfficeEntity> findByTownId(int townId){
        return this.entityManager.createQuery("FROM " + OfficeEntity.class.getName() +
                                                " office WHERE office.town.id=" + townId ).getResultList();
    }
}
