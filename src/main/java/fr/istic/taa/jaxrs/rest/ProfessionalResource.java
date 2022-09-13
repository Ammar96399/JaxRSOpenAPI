package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Professional;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professional")
@Produces({"application/json", "application/xml"})
public class ProfessionalResource {

    @GET
    @Path("{profId}")
    public Response getProfessionalById(@PathParam("profId") Long profId) {
        try {
            var dao = new ProfessionalDAO();
            return Response.ok().entity(dao.getById(profId)).build();
        } catch (NotFoundException e) {
            return Response.noContent().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response addProfessional(
            @Parameter(description = "Professional object that needs to be added to the store", required = true) Professional professional) {
        try {
            var dao = new ProfessionalDAO();
            dao.createProfessional(professional);
            return Response.ok().entity("Success").build();
        } catch (ValueAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @GET
    public List<Professional> getProfessionals() {
        var dao = new ProfessionalDAO();
        return dao.getAll();
    }
}
