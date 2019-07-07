package ru.ncedu.logistics.data.dao;

import ru.ncedu.logistics.data.entity.ProductEntity;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.Map;

@Stateless
public class ProductDAO extends LogisticsDAO<ProductEntity, Integer> {

    private static final String FIND_BY_NAME =
            "FROM " + ProductEntity.class.getName() + " product WHERE product.name = :name";

    private static final String EXIST_BY_NAME =
            "SELECT COUNT(product) FROM " + ProductEntity.class.getName() + " product WHERE product.name = :name";

    private static final String EXIST_BY_ID =
            "SELECT COUNT(product) FROM " + ProductEntity.class.getName() + " product WHERE product.id = :id";

    public ProductDAO(){
        init(ProductEntity.class);
    }

    public ProductEntity findByName(String name){
        Map<String, Object> args = new HashMap<>();
        args.put("name", name);

        return this.customFindSingleQuery(FIND_BY_NAME, args);
    }

    public boolean existsByName(String name){
        Map<String, Object> args = new HashMap<>();
        args.put("name", name);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_BY_NAME);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();

        em.close();

        return result > 0;
    }

    public boolean existsById(int id){
        Map<String, Object> args = new HashMap<>();
        args.put("id", id);

        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(EXIST_BY_ID);
        args.forEach(query::setParameter);

        long result = (long) query.getSingleResult();

        em.close();

        return result > 0;
    }
}
