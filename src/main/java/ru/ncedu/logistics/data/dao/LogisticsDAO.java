package ru.ncedu.logistics.data.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.Serializable;
import java.util.List;

public abstract class LogisticsDAO<T extends Serializable, PK extends Serializable> {

    @PersistenceContext(unitName = "logistics")
    protected EntityManager entityManager;

    private Class<T> cl;

    public void init(Class<T> cl) {
        this.cl = cl;
    }

    public T findById(PK id) {
        return this.entityManager.find(cl, id);
    }

    public List<T> findAll() {
        return this.entityManager.createQuery("FROM " + this.cl.getName() + " obj").getResultList();
    }

    public void create(T entity){
        this.entityManager.persist(entity);
    }

    public T update(T entity) {
        return this.entityManager.merge(entity);
    }

    public void delete(T entity) {
        this.entityManager.remove(entity);
    }

    public void deleteById(PK entityId) {
        T entity = findById(entityId);
        delete(entity);
    }

}
