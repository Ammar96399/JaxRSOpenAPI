package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.AppointmentDAO;
import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.domain.Appointment;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Professional;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.util.List;

@Path("/appointment")
@Produces({"application/json", "application/xml"})
public class AppointmentResource {

    @GET
    @Path("{appId}")
    public Appointment getProfessionalById(@PathParam("appId") Long appId) {
        var dao = new AppointmentDAO();
        return dao.getById(appId);
    }

    @POST
    @Consumes("application/json")
    public Response addAppointment(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true) Appointment appointment) {
        var dao = new AppointmentDAO();
        dao.addAppointment(appointment);
        return Response.ok().entity("Success").build();
    }

    @POST
    @Consumes("application/json")
    public Response addAppointmentByInfo(
            @Parameter(description = "Appointment object that needs to be added to the store", required = true)
                String reason, LocalDateTime startingTime, Patient patient, Professional professional) {
        var dao = new AppointmentDAO();
        dao.addAppointment(reason, startingTime, patient, professional);
        return Response.ok().entity("Success").build();
    }

    @GET
    public List<Appointment> getAppointments() {
        var dao = new AppointmentDAO();
        return dao.getAll();
    }
}
