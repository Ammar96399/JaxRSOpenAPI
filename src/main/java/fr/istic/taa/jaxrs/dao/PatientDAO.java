package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;

import javax.ws.rs.NotFoundException;
import java.util.List;

public class PatientDAO extends AbstractJpaDao<Long, Patient> {

    public PatientDAO() {
        super(Patient.class);
    }

    // Create queries

    public void createPatients(Patient patient) throws ValueAlreadyExistsException {
        if (manager.contains(patient)) {
            throw new ValueAlreadyExistsException("The patient object is already inserted.");
        }
        manager.getTransaction().begin();
        manager.persist(patient);
        manager.getTransaction().commit();
    }

    // Fetch queries

    public List<Patient> getPatientByName(String firstName, String lastName) {
        return manager
                .createQuery("SELECT p FROM Patient p WHERE p.lastName LIKE :lastName AND p.firstName LIKE :firstName", Patient.class)
                .setParameter("firstName", firstName)
                .setParameter("lastName", lastName)
                .getResultList();
    }

    public List<Patient> getPatientList() {
        return manager
                .createQuery("Select p From Patient p", Patient.class)
                .getResultList();
    }

    // Remove queries

    public void removePatientByName(String firstName, String lastName) {
        var res = manager.createQuery("select c from Child c where c.firstName like :first and c.lastName like :last", Child.class)
                .setParameter("first", firstName)
                .setParameter("last", lastName)
                .getResultList();
        if (res.isEmpty()) {
            throw new NotFoundException("The requested patient could not be find.");
        } else {
            this.manager.createQuery("delete from Patient p where p.firstName = :firstName and p.lastName = :lastName")
                    .setParameter("lastName", lastName)
                    .setParameter("firstName", firstName);
        }
    }

    // Update queries

    public void removeAppointmentToPatient(String firstName, String lastName, Long appointmentId) {
        var respat = manager.createQuery("select c from Child c where c.firstName like :first and c.lastName like :last", Child.class)
                .setParameter("first", firstName)
                .setParameter("last", lastName)
                .getResultList();
        if (respat.isEmpty()) {
            throw new NotFoundException("The requested patient could not be find.");
        }
        var res = manager.createQuery(
                "select app from Appointment app where app.id = :id and app.patient.lastName = :lastName " +
                        "and app.patient.firstName = :firstName", Appointment.class)
                .setParameter("id", appointmentId)
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName)
                .getResultList();
        if (res.isEmpty()) {
            throw new NotFoundException("The requested appointment could not be find.");
        }
        this.manager.createQuery("delete from Appointment app " +
                "where app.id = :id and app.patient.lastName = :lastName " +
                "and app.patient.firstName = :firstName")
                .setParameter("id", appointmentId)
                .setParameter("lastName", lastName)
                .setParameter("firstName", firstName);
    }
}
