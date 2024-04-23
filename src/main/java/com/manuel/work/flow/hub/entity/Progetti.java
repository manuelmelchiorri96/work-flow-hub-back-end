package com.manuel.work.flow.hub.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.Objects;


@Entity
public class Progetti implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Progetto")
    private int idProgetto;

    @Basic
    @Column(name = "Nome")
    private String nome;

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
    @Column(name = "Stato_Progetto")
    private String statoProgetto;

    @ManyToOne
    @JoinColumn(name = "ID_ProjectManager")
    private ProjectManager projectManager;

    @OneToMany(mappedBy = "progetto", cascade = CascadeType.ALL)
    private Collection<Task> tasks;


    public Progetti() {
 	
 	}
    
    
    public Progetti(int idProgetto, String nome, String descrizione, LocalDate dataInizio, LocalDate dataFinePrevista,
			String statoProgetto, ProjectManager projectManager, Collection<Task> tasks) {
		super();
		this.idProgetto = idProgetto;
		this.nome = nome;
		this.descrizione = descrizione;
		this.dataInizio = dataInizio;
		this.dataFinePrevista = dataFinePrevista;
		this.statoProgetto = statoProgetto;
		this.projectManager = projectManager;
		this.tasks = tasks;
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

	public ProjectManager getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(ProjectManager projectManager) {
		this.projectManager = projectManager;
	}

	public Collection<Task> getTasks() {
		return tasks;
	}

	public void setTasks(Collection<Task> tasks) {
		this.tasks = tasks;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Progetti progetti = (Progetti) o;

        if (idProgetto != progetti.idProgetto) return false;
        if (!Objects.equals(nome, progetti.nome)) return false;
        if (!Objects.equals(descrizione, progetti.descrizione))
            return false;
        if (!Objects.equals(dataInizio, progetti.dataInizio)) return false;
        if (!Objects.equals(dataFinePrevista, progetti.dataFinePrevista))
            return false;
        if (!Objects.equals(statoProgetto, progetti.statoProgetto))
            return false;
        return Objects.equals(projectManager, progetti.projectManager);
    }

    @Override
    public int hashCode() {
        int result = idProgetto;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (descrizione != null ? descrizione.hashCode() : 0);
        result = 31 * result + (dataInizio != null ? dataInizio.hashCode() : 0);
        result = 31 * result + (dataFinePrevista != null ? dataFinePrevista.hashCode() : 0);
        result = 31 * result + (statoProgetto != null ? statoProgetto.hashCode() : 0);
        result = 31 * result + (projectManager != null ? projectManager.hashCode() : 0);
        return result;
    }

}
