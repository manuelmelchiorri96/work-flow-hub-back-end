package com.manuel.work.flow.hub.validation;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.entity.ProjectManager;
import com.manuel.work.flow.hub.entity.Task;
import com.manuel.work.flow.hub.exception.InvalidDateException;
import com.manuel.work.flow.hub.exception.RequiredFieldsMissingException;
import com.manuel.work.flow.hub.vo.DipendenteVO;
import com.manuel.work.flow.hub.vo.ProgettoVO;
import com.manuel.work.flow.hub.vo.TaskVO;

import java.time.LocalDate;

public class ValidationUtils {

    public static void validateFields(String nome, String cognome, String email, String password, String ruolo, String expectedRuolo) {
        if (nome == null || nome.isEmpty() ||
                cognome == null || cognome.isEmpty() ||
                email == null || email.isEmpty() ||
                !isValidEmail(email) ||
                password == null || password.isEmpty() ||
                !isValidPassword(password) ||
                ruolo == null || ruolo.isEmpty() ||
                (!ruolo.equalsIgnoreCase(expectedRuolo))) {
            throw new RequiredFieldsMissingException("Assicurati di compilare tutti i campi correttamente, inclusa la selezione del ruolo come '" + expectedRuolo + "'.");
        }
    }

    public static void validateFields(String nome, String descrizione, String statoProgetto, LocalDate dataInizio, LocalDate dataFinePrevista, ProjectManager projectManager) {
        LocalDate oggi = LocalDate.now();

        if (nome == null || nome.isEmpty() ||
                descrizione == null || descrizione.isEmpty() ||
                statoProgetto == null || statoProgetto.isEmpty() ||
                projectManager == null) {
            throw new RequiredFieldsMissingException("Assicurati di compilare tutti i campi 'Nome', 'Descrizione', 'Stato Progetto', 'Data Inizio', 'Data Fine Prevista' e 'Project Manager'.");
        }

        if (dataInizio.isBefore(oggi) || dataFinePrevista.isBefore(oggi)) {
            throw new InvalidDateException("Le date non possono essere precedenti alla data odierna.");
        }
    }

    public static void validateFields(String descrizione, LocalDate dataInizio, LocalDate dataFinePrevista, String statoTask, Progetti progetto, Dipendenti dipendente) {
        LocalDate oggi = LocalDate.now();

        if (descrizione == null || descrizione.isEmpty() ||
                statoTask == null || statoTask.isEmpty()) {
            throw new RequiredFieldsMissingException("Assicurati di compilare tutti i campi 'Descrizione', 'Data Inizio', 'Data Fine Prevista', 'Stato Task' e 'Progetto'.");
        }

        if (dataInizio.isBefore(oggi) || dataFinePrevista.isBefore(oggi)) {
            throw new InvalidDateException("Le date non possono essere precedenti alla data odierna.");
        }
    }

    public static TaskVO convertToVO(Task task) {
        return new TaskVO(task.getIdTask(), task.getDescrizione(), task.getDataInizio(), task.getDataFinePrevista(), task.getStatoTask(), task.getProgetto().getIdProgetto(), task.getDipendente().getIdDipendente());
    }

    public static DipendenteVO convertToVO(Dipendenti dipendente) {
        return new DipendenteVO(dipendente.getIdDipendente(), dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail(), dipendente.getRuolo(), dipendente.getPassword());
    }

    public static ProgettoVO convertToVO(Progetti progetto) {
        return new ProgettoVO(progetto.getIdProgetto(), progetto.getNome(), progetto.getDescrizione(), progetto.getDataInizio(), progetto.getDataFinePrevista(), progetto.getStatoProgetto());
    }

    private static boolean isValidEmail(String email) {
        String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        return email.matches(emailPattern);
    }

    private static boolean isValidPassword(String password) {
        return password.length() >= 8;
    }

}

