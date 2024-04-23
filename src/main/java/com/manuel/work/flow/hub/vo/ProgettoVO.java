package com.manuel.work.flow.hub.vo;

import java.time.LocalDate;

public class ProgettoVO {

    private int idProgetto;

    private String nome;

    private String descrizione;

    private LocalDate dataInizio;

    private LocalDate dataFinePrevista;

    private String statoProgetto;

	public ProgettoVO(int idProgetto, String nome, String descrizione, LocalDate dataInizio, LocalDate dataFinePrevista,
			String statoProgetto) {
		super();
		this.idProgetto = idProgetto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFinePrevista = dataFinePrevista;
		this.statoProgetto = statoProgetto;
	}

	public ProgettoVO() {
		
	}

	public int getIdProgetto() {
		return idProgetto;
	}

	public void setIdProgetto(int idProgetto) {
		this.idProgetto = idProgetto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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

	public String getStatoProgetto() {
		return statoProgetto;
	}

	public void setStatoProgetto(String statoProgetto) {
		this.statoProgetto = statoProgetto;
	}
    
    

}
