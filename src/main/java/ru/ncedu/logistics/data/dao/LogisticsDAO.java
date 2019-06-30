package ru.ncedu.logistics.data.dao;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class LogisticsDAO<T extends Serializable, PK extends Serializable> {

    @PersistenceUnit(unitName = "logistics")
    private EntityManagerFactory entityManagerFactory;

    private Class<T> cl;

    public void init(Class<T> cl) {
        this.cl = cl;
    }

    public T findById(PK id) {
        EntityManager em = entityManagerFactory.createEntityManager();
        T result = em.find(cl, id);
        em.close();
        return result;
    }

    public List<T> findAll() {
        return customFindListQuery("FROM " + this.cl.getName() + " obj");
    }

    public List<T> customFindListQuery(String queryText) {
        return customFindListQuery(queryText, null);
    }

    public List<T> customFindListQuery(String queryText, Map<String, Object> parameters) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(queryText);
        if(parameters != null) {
            parameters.forEach(query::setParameter);
        }
        List<T> result = (List<T>) query.getResultList();
        em.close();
        return result;
    }

    public T customFindSingleQuery(String queryText) {
        return customFindSingleQuery(queryText, null);
    }

    public T customFindSingleQuery(String queryText, Map<String, Object> parameters) {
        EntityManager em = entityManagerFactory.createEntityManager();
        Query query = em.createQuery(queryText);
        if(parameters != null) {
            parameters.forEach(query::setParameter);
        }
        T result = (T) query.getSingleResult();
        em.close();
        return result;
    }

    public void create(T entity){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
    }

    public T update(T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        T result = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return result;
    }

    public void delete(T entity) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        if (!em.contains(entity)) {
            entity = em.merge(entity);
        }
        em.remove(entity);
        em.getTransaction().commit();
        em.close();
    }

    public void deleteById(PK entityId) {
        T entity = findById(entityId);
        delete(entity);
    }

}
