package com.thorstiland.spielplan.service;

import java.io.Serializable;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public abstract class CrudService<T> {

    @PersistenceContext
    EntityManager entityManager;

    private Class<T> clazz;

    public CrudService(Class<T> clazz){
        this.clazz = clazz;
    }

    /*
     * A "copy" of the Hibernate's API as this doesn't exist
     * in JPA.
     */
    public enum MatchMode {
        START,
        END,
        EXACT,
        ANYWHERE
    }

    /**
     * Flush EM
     */
    public void flush() {
        entityManager.flush();
    }

    /**
     * Saves an entity.
     * @param entity
     * @return newly created entity.
     */
    public T save(T entity) {
        entityManager.persist(entity);
        return entity;
    }

    /**
     * Merges objects with the same identifier within a session into a newly
     * created object.
     * @param entity
     * @return a newly created instance merged.
     */
    public T merge(T entity) {
        return entityManager.merge(entity);
    }

    /**
     * Deletes tne entity.
     * @param id
     * @throws Exception
     * @throws EntityNotFoundException if the id does not exist.
     */
    public void delete(Serializable id) throws EntityNotFoundException {
        T entity = find(id);
        if (entity != null) {
            entityManager.remove(entity);
        } else {
            throw new EntityNotFoundException();
        }
    }

    /**
     * Find an entity by its identifier.
     * @param id
     * @return
     */
    public T find(Serializable id) {
        return entityManager.find(clazz, id);
    }
    /**
     * Finds all objects of an entity class.
     * 
     * @param clazz the entity class.
     * @return
     */
    public List<T> findAll() {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(clazz);
        cq.from(clazz);
        
        return entityManager.createQuery(cq).getResultList();
    }
}
