package dao;

import exceptions.Exceptions;
import model.Book;
import model.Client;
import org.springframework.context.annotation.ComponentScan;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Transactional
@ComponentScan(basePackages = "aspects")
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

    public void showDetails(List<?> entities, String message) {
        System.out.println("==" + message + "==");
        for (Object entity : entities) {
            System.out.println(entity);
        }
        System.out.println("//////////////////////////////");
    }

    public void borrowBook(Long clientId, Long bookId) {
        if (isClientInDatabase(clientId) && isBookInDataBase(bookId)) {
            Client client = entityManager.find(Client.class, clientId);
            Book book = entityManager.find(Book.class, bookId);
            if (book.isAvailable()) {
                book.setClient(client);
                book.setAvailable(false);
            } else {
                System.out.println("Book is not available!");
            }
        } else {
            throw new Exceptions(Exceptions.NO_ENTITY_IN_DB);
        }
    }

    private boolean isClientInDatabase(Long clientId) {
        return entityManager.find(Client.class, clientId) != null;
    }

    private boolean isBookInDataBase(Long bookId) {
        return entityManager.find(Book.class, bookId) != null;
    }
}
