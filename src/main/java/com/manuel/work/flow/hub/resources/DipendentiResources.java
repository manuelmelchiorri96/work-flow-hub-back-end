package com.manuel.work.flow.hub.resources;

import com.manuel.work.flow.hub.dto.CredenzialiDTO;
import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.exception.EmailAlreadyExistsException;
import com.manuel.work.flow.hub.exception.InvalidCredentialsException;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.exception.RequiredFieldsMissingException;
import com.manuel.work.flow.hub.repository.GeneralRepositoryImpl;
import com.manuel.work.flow.hub.service.DipendenteService;
import com.manuel.work.flow.hub.service.DipendenteServiceImpl;
import com.manuel.work.flow.hub.vo.DipendenteVO;
import jakarta.persistence.Persistence;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/dipendenti")
public class DipendentiResources {

    private final DipendenteService dipendenteService =
            new DipendenteServiceImpl(
                    new GeneralRepositoryImpl<>(
                            Persistence
                                    .createEntityManagerFactory("persistence_unit")
                                    .createEntityManager(), Dipendenti.class));

    @POST
    @Path("/login-dipendente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response loginDipendente(CredenzialiDTO credentials) {
        try {
            DipendenteVO loggedUser = dipendenteService.login(credentials.getEmail(), credentials.getPassword());
            return Response.ok(loggedUser).build();
        } catch (InvalidCredentialsException e) {
            return Response.status(Response.Status.UNAUTHORIZED).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il login: " + e.getMessage()).build();
        }
    }

    @POST
    @Path("/register-dipendente")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerDipendente(Dipendenti dipendente) {
        try {
            Dipendenti registeredUser = dipendenteService.register(dipendente);
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
    public Response updateAccount(Dipendenti dipendente) {
        try {
            Dipendenti updatedUser = dipendenteService.updateAccount(dipendente);
            return Response.ok(updatedUser).build();
        } catch (IllegalArgumentException | RequiredFieldsMissingException e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'aggiornamento dell'account: " + e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{idDipendente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteAccount(@PathParam("idDipendente") long idDipendente) {
        try {
            dipendenteService.deleteAccount(idDipendente);
            return Response.ok("Account eliminato con successo").build();
        } catch (IllegalArgumentException e) {
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        } catch (RuntimeException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante l'eliminazione dell'account: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/all")
    @Produces(MediaType.APPLICATION_JSON)
    public Response findAllDipendenti() {
        try{
            return Response.ok(dipendenteService.findAllDipendenti()).build();
        }catch (ListaVuotaException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero dei dipendenti: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idDipendente}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDipendente(@PathParam("idDipendente") int idDipendente) {
        try{
            return Response.ok(dipendenteService.getDipendente(idDipendente)).build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero del dipendente: " + e.getMessage()).build();
        }
    }

    @GET
    @Path("/{idDipendente}/vo")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDipendenteVO(@PathParam("idDipendente") int idDipendente) {
        try{
            return Response.ok(dipendenteService.getDipendenteVO(idDipendente)).build();
        }catch (IllegalArgumentException e){
            return Response.status(Response.Status.NOT_FOUND).entity(e.getMessage()).build();
        }catch(RuntimeException e){
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Errore durante il recupero del dipendente: " + e.getMessage()).build();
        }
    }
}
