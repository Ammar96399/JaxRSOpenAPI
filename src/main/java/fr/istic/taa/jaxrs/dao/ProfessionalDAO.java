package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.domain.Professional;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class ProfessionalDAO extends AbstractJpaDao<Long, Professional> {

    public ProfessionalDAO() {
        super(Professional.class);
    }

    // Create

    public void createProfessional(Professional professional) throws ValueAlreadyExistsException {
        if (manager.contains(professional)) {
            throw new ValueAlreadyExistsException("The professional object is already inserted.");
        }
        var tx = manager.getTransaction();
        tx.begin();
        manager.persist(professional);
        tx.commit();
    }

    // Get

    public List<Professional> getProfessionalByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p FROM Professional p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName", Professional.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<Professional> listProfessionals() {
        return manager
                .createQuery("SELECT p FROM Professional p", Professional.class)
                .getResultList();
    }

    // Remove queries

    public void removeProfessionalByName(String firstName, String lastName) {
        var res = manager.createQuery("select c from Professional c where c.firstName like :first and c.lastName like :last", Professional.class)
                .setParameter("first", firstName)
                .setParameter("last", lastName)
                .getResultList();
        if (res.isEmpty()) {
            throw new NotFoundException("The requested professional could not be find.");
        } else {
            this.manager.createQuery("delete from Professional p where p.firstName = :firstName and p.lastName = :lastName")
                    .setParameter("lastName", lastName)
                    .setParameter("firstName", firstName);
        }
    }

}
