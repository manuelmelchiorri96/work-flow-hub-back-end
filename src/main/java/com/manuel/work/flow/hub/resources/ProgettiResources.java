package com.manuel.work.flow.hub.resources;

import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.exception.InvalidDateException;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.exception.RequiredFieldsMissingException;
import com.manuel.work.flow.hub.repository.GeneralRepositoryImpl;
import com.manuel.work.flow.hub.service.ProgettiService;
import com.manuel.work.flow.hub.service.ProgettiServiceImpl;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/progetti")
public class ProgettiResources {

    private final ProgettiService progettiService =
            new ProgettiServiceImpl(
                    new GeneralRepositoryImpl<>(
                            Persistence
                                    .createEntityManagerFactory("persistence_unit")
                                    .createEntityManager(), Progetti.class));


    @POST
    @Path("/save-project")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveProject(Progetti progetto) {
        try {
            Progetti registeredProject = progettiService.saveProject(progetto);
            return Response.ok(registeredProject).build();
        } catch (RequiredFieldsMissingException | IllegalArgumentException | InvalidDateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante la registrazione: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update-project")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateProject(Progetti progetto) {
        try {
            Progetti updatedProject = progettiService.updateProject(progetto);
            return Response.ok(updatedProject).build();
        } catch (RequiredFieldsMissingException | IllegalArgumentException | InvalidDateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'aggiornamento del progetto: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idProject}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteProject(@PathParam("idProject") long idProject) {
        try {
            progettiService.deleteProject(idProject);
            return Response.ok("Progetto eliminato con successo").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'eliminazione del progetto: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllProgetti() {
        try{
            return Response.ok(progettiService.findAllProgetti()).build();
        }catch (ListaVuotaException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero dei progetti: " + e.getMessage()).build();
        }
    }

}
