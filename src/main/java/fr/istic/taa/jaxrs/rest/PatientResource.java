package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.PatientDAO;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/patient")
@Produces({"application/json", "application/xml"})
public class PatientResource {

    @GET
    @Path("/{patientId}")
    public Response getPatientById(@PathParam("patientId") Long patientId) {
        try {
            var patientDao = new PatientDAO();
            return Response.ok().entity(patientDao.getById(patientId)).build();
        } catch (NotFoundException e) {
            return Response.noContent().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response addPatient(
            @Parameter(description="Patient object that needs to be added to the store", required = true) Patient patient
    ) {
        try {
            var patientDao = new PatientDAO();
            patientDao.createPatients(patient);
            return Response.ok().entity("SUCCESS").build();
        } catch (ValueAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{patientId}")
    public void removePatientById(@PathParam("patientId") Long patientId) {
        var patientDao = new PatientDAO();
        patientDao.deleteById(patientId);
    }
}
