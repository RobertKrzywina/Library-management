package dao;

import exceptions.Exceptions;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;

@Transactional
public abstract class GenericDao<T, K> {

    @PersistenceContext
    private EntityManager entityManager;
    private Class<T> type;

    @SuppressWarnings("unchecked")
    public GenericDao() {
        type = (Class<T>) ((ParameterizedType) this.getClass()
                .getGenericSuperclass())
                .getActualTypeArguments()[0];
    }

    public void save(T entity) {
        entityManager.persist(entity);
    }

    public T get(K id) {
        return entityManager.find(type, id);
    }

    public void delete(K id) {
        T entity = entityManager.find(type, id);
        if (entity != null) {
            entityManager.remove(entity);
        } else {
            throw new Exceptions(Exceptions.NO_ENTITY_IN_DB);
        }
    }
}
