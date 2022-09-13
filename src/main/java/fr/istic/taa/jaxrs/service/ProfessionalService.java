package fr.istic.taa.jaxrs.service;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Professional;

import java.util.List;

public class ProfessionalService {
    private ProfessionalDAO professionalDAO;

    public ProfessionalService() {
        this.professionalDAO = new ProfessionalDAO();
    }

    public List<Professional> getPatientByName(String firstName, String lastName) {
        return professionalDAO.getProfessionalByName(firstName, lastName);
    }

    public List<Professional> getPatientList() {
        return professionalDAO.listProfessionals();
    }

    public void removePatientByName(String firstName, String lastName) {
        professionalDAO.removeProfessionalByName(firstName, lastName);
    }
}
