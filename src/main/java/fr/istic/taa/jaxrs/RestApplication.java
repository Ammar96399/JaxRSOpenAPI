package fr.istic.taa.jaxrs;

import fr.istic.taa.jaxrs.rest.ProfessionalResource;
import io.swagger.v3.jaxrs2.integration.resources.OpenApiResource;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/")
public class RestApplication extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        final Set<Class<?>> resources = new HashSet<>();


        // SWAGGER endpoints
        resources.add(OpenApiResource.class);

        //Your own resources.
        resources.add(ProfessionalResource.class);

        return resources;
    }
}