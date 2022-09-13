package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Professional;

import java.util.List;

public class ProfessionalDAO extends AbstractJpaDao<Long, Professional> {

    public ProfessionalDAO() {
        super(Professional.class);
    }

    // Create

    public void createProfessional(Professional professional) {
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
        this.manager.createQuery("delete from Professional p where p.firstName = :firstName and p.lastName = :lastName")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }

}
