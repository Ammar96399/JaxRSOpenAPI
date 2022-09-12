package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Child;

import java.util.List;

public class ChildDAO extends AbstractJpaDao<Long, Child> {

    public ChildDAO() {
        super(Child.class);
    }

    public void createChild(String firstName, String lastName, String parentName, Integer age) {
        var tx = manager.getTransaction();
        tx.begin();
        manager.persist(new Child(firstName, lastName, parentName, age));
        tx.commit();
    }

    // We think that returning a list is better than returning a single element from a list.
    public List<Child> getChildByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT c FROM Child c WHERE c.lastName LIKE :lastName " +
                        "AND c.firstName LIKE :firstName", Child.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<Child> getChildList() {
        return manager
                .createQuery("SELECT c FROM Child c", Child.class)
                .getResultList();
    }

    public void removeChildByName(String firstName, String lastName) {
        this.manager.createQuery("delete from Child c where c.firstName = :firstName and c.lastName = :lastName")
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }

    public void removeAppointmentToPatient(String firstName, String lastName, Long appointmentId) {
        this.manager.createQuery("delete from Appointment app " +
                        "where app.id = :id and app.patient.lastName = :lastName " +
                        "and app.patient.firstName = :firstName")
                .setParameter("id", appointmentId)
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }
}
