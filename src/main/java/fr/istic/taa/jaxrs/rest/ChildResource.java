package fr.istic.taa.jaxrs.rest;

import fr.istic.taa.jaxrs.dao.ChildDAO;
import fr.istic.taa.jaxrs.domain.Child;
import fr.istic.taa.jaxrs.domain.Patient;
import io.swagger.v3.oas.annotations.Parameter;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("/patient/child")
@Produces({"application/json", "application/xml"})
public class ChildResource {
    @GET
    @Path("/{childId}")
    public Patient getChildById(@PathParam("childId") Long childId) {
        var childDao = new ChildDAO();
        return childDao.getById(childId);
    }

    @POST
    @Consumes("application/json")
    public Response addChild(
            @Parameter(description="Patient object that needs to be added to the store", required = true) Child child
    ) {
        var childDAO = new ChildDAO();
        childDAO.createChild(child);
        return Response.ok().entity("SUCCESS").build();
    }

    @DELETE
    @Path("/{childId}")
    public void removeChildById(@PathParam("childId") Long childId) {
        var childDAO = new ChildDAO();
        childDAO.deleteById(childId);
    }
}
