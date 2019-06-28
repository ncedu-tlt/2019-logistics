package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.ProductEntity;

import javax.ejb.Stateless;

@Stateless
public class ProductDAO extends LogisticsDAO<ProductEntity, Integer> {

    public ProductDAO(){
        init(ProductEntity.class);
    }
}
