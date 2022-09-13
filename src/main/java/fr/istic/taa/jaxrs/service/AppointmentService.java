package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.AppointmentDAO;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Professional;
import org.checkerframework.checker.units.qual.A;

import java.time.LocalDateTime;
import java.util.List;

public class AppointmentService {

    public AppointmentDAO appointmentDAO;

    public AppointmentService() {
        this.appointmentDAO = new AppointmentDAO();
    }

    public void addAppointment(String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        appointmentDAO.addAppointment(reason, startingTime, patient, professional);
    }

    public void addAppointment(Appointment appointment) {
        appointmentDAO.addAppointment(appointment);
    }

    public List<Appointment> getAppointmentByPatientId(Long id) {
        return appointmentDAO.getAppointmentsByPatientId(id);
    }

    public void updateStartingTime(Long id, LocalDateTime startingTime) {
        appointmentDAO.updateStartingTime(id, startingTime);
    }

    public void removeById(Long id) {
        appointmentDAO.removeById(id);
    }
}
