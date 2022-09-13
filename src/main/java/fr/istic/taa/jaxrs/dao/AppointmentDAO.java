package fr.istic.taa.jaxrs.dao;

import fr.istic.taa.jaxrs.dao.generic.AbstractJpaDao;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Professional;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;

import javax.ws.rs.NotFoundException;
import java.time.LocalDateTime;
import java.util.List;

public class AppointmentDAO extends AbstractJpaDao<Long, Appointment> {
    public AppointmentDAO() {
        super(Appointment.class);
    }

    public void addAppointment(String reason, LocalDateTime startingTime, Patient patient, Professional professional) throws ValueAlreadyExistsException {
        var res = manager.createQuery("select p from Appointment p where p.patient = :patient and p.startingTime = :start and p.professional = :prof", Appointment.class)
                .setParameter("patient", patient)
                .setParameter("prof", professional)
                .setParameter("start", startingTime)
                .getResultList();
        if (res.isEmpty()) {
            var tx = manager.getTransaction();
            tx.begin();
            manager.persist(new Appointment(reason, startingTime, patient, professional));
            tx.commit();
        } else {
            throw new ValueAlreadyExistsException("The time span of the given patient and professional is taken.");
        }
    }

    public void addAppointment(Appointment appointment) throws ValueAlreadyExistsException {
        if (manager.contains(appointment)) {
            throw new ValueAlreadyExistsException("The appointment object is already inserted.");
        }
        var tx = manager.getTransaction();
        tx.begin();
        manager.persist(appointment);
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
    public void updateStartingTime(Long id, LocalDateTime startingTime) throws NotFoundException {
        if (this.exists(id)) {
            manager.createQuery("UPDATE Appointment a SET a.startingTime = :startingTime WHERE a.id = :id")
                    .setParameter("startingTime", startingTime)
                    .setParameter("id", id);
        } else {
            throw new NotFoundException("The requested appointment could not be find.");
        }
    }

    //remove
    public void removeById(Long id) throws NotFoundException {
        if (this.exists(id)) {
            manager.createQuery("DELETE FROM Appointment a WHERE a.id = :id")
                    .setParameter("id", id);
        } else {
            throw new NotFoundException("The requested appointment could not be find.");
        }
    }
}
