package com.manuel.work.flow.hub.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;


@Entity
public class Task implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Task")
    private int idTask;

    @Basic
    @Column(name = "Descrizione")
    private String descrizione;

    @Basic
    @Column(name = "Data_Inizio")
    private LocalDate dataInizio;

    @Basic
    @Column(name = "Data_Fine_Prevista")
    private LocalDate dataFinePrevista;

    @Basic
    @Column(name = "Stato_Task")
    private String statoTask;

    @ManyToOne
    @JoinColumn(name = "ID_Progetto")
    private Progetti progetto;

    @ManyToOne
    @JoinColumn(name = "ID_Dipendente")
    private Dipendenti dipendente;

    
    public Task() {
		
	}

    public Task(int idTask, String descrizione, LocalDate dataInizio, LocalDate dataFinePrevista, String statoTask,
			Progetti progetto, Dipendenti dipendente) {
		super();
		this.idTask = idTask;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFinePrevista = dataFinePrevista;
		this.statoTask = statoTask;
		this.progetto = progetto;
		this.dipendente = dipendente;
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

	public Progetti getProgetto() {
		return progetto;
	}

	public void setProgetto(Progetti progetto) {
		this.progetto = progetto;
	}

	public Dipendenti getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendenti dipendente) {
		this.dipendente = dipendente;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Task task = (Task) o;

        if (idTask != task.idTask) return false;
        if (!Objects.equals(descrizione, task.descrizione)) return false;
        if (!Objects.equals(dataInizio, task.dataInizio)) return false;
        if (!Objects.equals(dataFinePrevista, task.dataFinePrevista))
            return false;
        if (!Objects.equals(statoTask, task.statoTask)) return false;
        if (!Objects.equals(progetto, task.progetto)) return false;
        return Objects.equals(dipendente, task.dipendente);
    }

    @Override
    public int hashCode() {
        int result = idTask;
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (dataInizio != null ? dataInizio.hashCode() : 0);
        result = 31 * result + (dataFinePrevista != null ? dataFinePrevista.hashCode() : 0);
        result = 31 * result + (statoTask != null ? statoTask.hashCode() : 0);
        result = 31 * result + (progetto != null ? progetto.hashCode() : 0);
        result = 31 * result + (dipendente != null ? dipendente.hashCode() : 0);
        return result;
    }
}
