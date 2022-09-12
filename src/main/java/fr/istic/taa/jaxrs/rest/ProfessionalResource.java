package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ProfessionalDAO;
import fr.istic.taa.jaxrs.domain.Pet;
import fr.istic.taa.jaxrs.domain.Professional;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/professional")
@Produces({"application/json", "application/xml"})
public class ProfessionalResource {

    @GET
    @Path("{profId}")
    public Professional getProfessionalById(@PathParam("profId") Long profId) {
        var dao = new ProfessionalDAO();
        return dao.getById(profId);
    }

    @POST
    @Consumes("application/json")
    public Response addProfessional(
            @Parameter(description = "Professional object that needs to be added to the store", required = true) Professional professional) {
        var dao = new ProfessionalDAO();
        dao.createProfessional(professional);
        return Response.ok().entity("Success").build();
    }

    @POST
    @Consumes("application/json")
    public Response addProfessionalByNames(
            @Parameter(description = "Professional object that needs to be added to the store", required = true) String firstname, String lastname) {
        var dao = new ProfessionalDAO();
        dao.createProfessional(firstname, lastname);
        return Response.ok().entity("Success").build();
    }

    @GET
    public List<Professional> getProfessionals() {
        var dao = new ProfessionalDAO();
        return dao.getAll();
    }
}
