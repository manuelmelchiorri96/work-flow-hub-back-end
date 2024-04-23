package com.manuel.work.flow.hub.resources;

import com.manuel.work.flow.hub.entity.Task;
import com.manuel.work.flow.hub.exception.InvalidDateException;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.exception.RequiredFieldsMissingException;
import com.manuel.work.flow.hub.repository.GeneralRepositoryImpl;
import com.manuel.work.flow.hub.service.TaskService;
import com.manuel.work.flow.hub.service.TaskServiceImpl;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/tasks")
public class TaskResources {

    private final TaskService taskService =
            new TaskServiceImpl(
                    new GeneralRepositoryImpl<>(
                            Persistence
                                    .createEntityManagerFactory("persistence_unit")
                                    .createEntityManager(), Task.class));


    @POST
    @Path("/save-task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveTask(Task task) {
        try {
            Task savedTask = taskService.saveTask(task);
            return Response.ok(savedTask).build();
        } catch (RequiredFieldsMissingException | IllegalArgumentException | InvalidDateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante la registrazione: " + e.getMessage()).build();
        }
    }

    @PUT
    @Path("/update-task")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateTask(Task task) {
        try {
            Task updatedTask = taskService.updateTask(task);
            return Response.ok(updatedTask).build();
        } catch (RequiredFieldsMissingException | IllegalArgumentException | InvalidDateException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l' aggiornamento: " + e.getMessage()).build();
        }
    }


    @GET
    @Path("/all-tasks")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllTasks() {
        try{
            return Response.ok(taskService.findAllTask()).build();
        }catch (ListaVuotaException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero dei tasks: " + e.getMessage()).build();
        }
    }


    @GET
    @Path("/all-tasks-dipendenti/{idDipendente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllTasksByDipendente(@PathParam("idDipendente") int idDipendente) {
        try{
            return Response.ok(taskService.findTaskByDipendente(idDipendente)).build();
        }catch (ListaVuotaException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero dei tasks: " + e.getMessage()).build();
        }
    }


    @GET
    @Path("/all-tasks-progetto/{idProgetto}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllTasksByProgetto(@PathParam("idProgetto") int idProgetto) {
        try{
            return Response.ok(taskService.findTaskByProgetto(idProgetto)).build();
        }catch (ListaVuotaException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero dei tasks: " + e.getMessage()).build();
        }
    }

}
