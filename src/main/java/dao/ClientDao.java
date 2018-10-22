package dao;

import exceptions.Exceptions;
import model.Client;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class ClientDao extends GenericDao<Client, Long> {

    @PersistenceContext
    private EntityManager entityManager;

    public void updateStudentStatus(Long id, boolean isStudent) {
        Client client = entityManager.find(Client.class, id);
        if (client != null) {
            client.setStudent(isStudent);
            entityManager.persist(client);
        } else {
            throw new Exceptions(Exceptions.NO_CLIENT_IN_DB);
        }
    }
}
