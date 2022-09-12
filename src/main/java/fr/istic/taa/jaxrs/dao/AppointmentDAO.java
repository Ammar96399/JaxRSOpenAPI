package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Professional;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDAO extends AbstractJpaDao<Long, Appointment> {
    public AppointmentDAO() {
        super(Appointment.class);
    }

    public void addAppointment(String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        var tx = manager.getTransaction();
        tx.begin();
        manager.persist(new Appointment(reason, startingTime, patient, professional));
        tx.commit();
    }
    // Access appointments
    public List<Appointment> getAppointmentsByProfessionalId(Long id) {
        return manager.createQuery("SELECT p FROM Appointment p WHERE p.professional.id = :id", Appointment.class)
                .setParameter("id", id)
                .getResultList();
    }

    public List<Appointment> getAppointmentsByPatientId(Long id) {
        return manager.createQuery("SELECT p FROM Appointment p WHERE p.patient.id = :id", Appointment.class)
                .setParameter("id", id)
                .getResultList();
    }

    //update
    public void updateStartingTime(Long id, LocalDateTime startingTime) {
        manager.createQuery("UPDATE Appointment a SET a.startingTime = :startingTime WHERE a.id = :id")
                .setParameter("startingTime", startingTime)
                .setParameter("id", id);
    }

    //remove
    public void removeById(Long id) {
        manager.createQuery("DELETE FROM Appointment a WHERE a.id = :id")
                .setParameter("id", id);
    }
}
