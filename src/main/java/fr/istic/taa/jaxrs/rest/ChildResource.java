package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ChildDAO;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.domain.Patient;
import fr.istic.taa.jaxrs.exceptions.ValueAlreadyExistsException;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/patient/child")
@Produces({"application/json", "application/xml"})
public class ChildResource {
    @GET
    @Path("/{childId}")
    public Response getChildById(@PathParam("childId") Long childId) {
        try {
            var childDao = new ChildDAO();
            return Response.ok().entity(childDao.getById(childId)).build();
        } catch (NotFoundException e) {
            return Response.noContent().entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes("application/json")
    public Response addChild(
            @Parameter(description="Patient object that needs to be added to the store", required = true) Child child
    ) {
        try {
            var childDAO = new ChildDAO();
            childDAO.createChild(child);
            return Response.ok().entity("SUCCESS").build();
        } catch (ValueAlreadyExistsException e) {
            return Response.status(409).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{childId}")
    public void removeChildById(@PathParam("childId") Long childId) {
        var childDAO = new ChildDAO();
        childDAO.deleteById(childId);
    }
}
