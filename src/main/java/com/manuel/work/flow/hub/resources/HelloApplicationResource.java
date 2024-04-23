package com.manuel.work.flow.hub.resources;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

@Path("/hello-application")
public class HelloApplicationResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String helloWorld() {
        return "Hello, WorkFlowHub";
    }

}
