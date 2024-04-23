package com.manuel.work.flow.hub.vo;

import java.time.LocalDate;

public class TaskVO {

    private int idTask;

    private String descrizione;

    private LocalDate dataInizio;

    private LocalDate dataFinePrevista;

    private String statoTask;

    private int idProgetto;

    private int idDipendente;

	public TaskVO(int idTask, String descrizione, LocalDate dataInizio, LocalDate dataFinePrevista, String statoTask,
			int idProgetto, int idDipendente) {
		super();
		this.idTask = idTask;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFinePrevista = dataFinePrevista;
		this.statoTask = statoTask;
		this.idProgetto = idProgetto;
		this.idDipendente = idDipendente;
	}

	public TaskVO() {
		super();
	}

	public int getIdTask() {
		return idTask;
	}

	public void setIdTask(int idTask) {
		this.idTask = idTask;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public LocalDate getDataInizio() {
		return dataInizio;
	}

	public void setDataInizio(LocalDate dataInizio) {
		this.dataInizio = dataInizio;
	}

	public LocalDate getDataFinePrevista() {
		return dataFinePrevista;
	}

	public void setDataFinePrevista(LocalDate dataFinePrevista) {
		this.dataFinePrevista = dataFinePrevista;
	}

	public String getStatoTask() {
		return statoTask;
	}

	public void setStatoTask(String statoTask) {
		this.statoTask = statoTask;
	}

	public int getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}

	public int getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(int idDipendente) {
		this.idDipendente = idDipendente;
	}
    
    
    

}
