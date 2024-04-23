package com.manuel.work.flow.hub.resources;

import com.manuel.work.flow.hub.dto.CredenzialiDTO;
import com.manuel.work.flow.hub.entity.ProjectManager;
import com.manuel.work.flow.hub.exception.EmailAlreadyExistsException;
import com.manuel.work.flow.hub.exception.InvalidCredentialsException;
import com.manuel.work.flow.hub.exception.RequiredFieldsMissingException;
import com.manuel.work.flow.hub.repository.GeneralRepositoryImpl;
import com.manuel.work.flow.hub.service.*;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/project-manager")
public class ProjectManagerResources {

    private final ProjectManagerService projectManagerService =
            new ProjectManagerServiceImpl(
                    new GeneralRepositoryImpl<>(
                            Persistence
                                    .createEntityManagerFactory("persistence_unit")
                                    .createEntityManager(), ProjectManager.class));


    @POST
    @Path("/login-project-manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginProjectManager(CredenzialiDTO credentials) {
        try {
            ProjectManager loggedUser = projectManagerService.login(credentials.getEmail(), credentials.getPassword());
            return Response.ok(loggedUser).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il login: " + e.getMessage()).build();
        }
    }


    @POST
    @Path("/register-project-manager")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerProjectManager(ProjectManager projectManager) {
        try {
            ProjectManager registeredUser = projectManagerService.register(projectManager);
            return Response.ok(registeredUser).build();
        } catch (RequiredFieldsMissingException | EmailAlreadyExistsException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante la registrazione: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAccount(ProjectManager projectManager) {
        try {
            ProjectManager updatedUser = projectManagerService.updateAccount(projectManager);
            return Response.ok(updatedUser).build();
        } catch (IllegalArgumentException | RequiredFieldsMissingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'aggiornamento dell'account: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idProjectManager}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("idProjectManager") long idProjectManager) {
        try {
            projectManagerService.deleteAccount(idProjectManager);
            return Response.ok("Account eliminato con successo").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'eliminazione dell'account: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idProjectManager}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getProjectManager(@PathParam("idProjectManager") int idProjectManager) {
        try{
            return Response.ok(projectManagerService.getProjectManager(idProjectManager)).build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero del project-manager: " + e.getMessage()).build();
        }
    }
}

