package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.AppointmentDAO;
import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Professional;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Path("/appointment")
@Produces({"application/json", "application/xml"})
public class AppointmentResource {

    @GET
    @Path("{appId}")
    public Response getProfessionalById(@PathParam("appId") Long appId) {
        var dao = new AppointmentDAO();
        var res = dao.getById(appId);
        if (Objects.nonNull(res)) {
            return Response.ok().entity(res).build();
        } else {
            return Response.status(404).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response addAppointment(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true) Appointment appointment) {
        var dao = new AppointmentDAO();
        try {
            dao.addAppointment(appointment);
            return Response.ok().entity("Success").build();
        } catch (ValueAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response addAppointmentByInfo(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true)
                String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        var dao = new AppointmentDAO();
        try {
            dao.addAppointment(reason, startingTime, patient, professional);
            return Response.ok().entity("Success").build();
        } catch (ValueAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Appointment> getAppointments() {
        var dao = new AppointmentDAO();
        return dao.getAll();
    }
}
