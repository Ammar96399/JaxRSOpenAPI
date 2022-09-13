package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.PatientDAO;
import fr.istic.taa.jaxrs.domain.Patient;

import java.util.List;

public class PatientService {
    private PatientDAO patientDAO;

    public PatientService() {
        this.patientDAO = new PatientDAO();
    }

    public List<Patient> getPatientByName(String firstName, String lastName) {
        return patientDAO.getPatientByName(firstName, lastName);
    }

    public List<Patient> getPatientList() {
        return patientDAO.getPatientList();
    }

    public void removePatientByName(String firstName, String lastName) {
        patientDAO.removePatientByName(firstName, lastName);
    }

    public void removeAppointmentToPatient(String firstName, String lastName, Long appointmentId) {
        patientDAO.removeAppointmentToPatient(firstName, lastName, appointmentId);
    }
}