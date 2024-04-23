package com.manuel.work.flow.hub.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Collection;
import java.util.Objects;

@Entity
public class Dipendenti implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_Dipendente")
    private int idDipendente;

    @Basic
    @Column(name = "Nome")
    private String nome;

    @Basic
    @Column(name = "Cognome")
    private String cognome;

    @Basic
    @Column(name = "Email")
    private String email;

    @Basic
    @Column(name = "Ruolo")
    private String ruolo;

    @Basic
    @Column(name = "Password")
    private String password;

    @OneToMany(mappedBy = "dipendente")
    private Collection<Task> tasksByIdDipendente;

    
    public Dipendenti() {
		
	}
    
    public Dipendenti(int idDipendente, String nome, String cognome, String email, String ruolo, String password,
			Collection<Task> tasksByIdDipendente) {
		super();
		this.idDipendente = idDipendente;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ruolo = ruolo;
		this.password = password;
		this.tasksByIdDipendente = tasksByIdDipendente;
	}

	public int getIdDipendente() {
		return idDipendente;
	}

	public void setIdDipendente(int idDipendente) {
		this.idDipendente = idDipendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRuolo() {
		return ruolo;
	}

	public void setRuolo(String ruolo) {
		this.ruolo = ruolo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Collection<Task> getTasksByIdDipendente() {
		return tasksByIdDipendente;
	}

	public void setTasksByIdDipendente(Collection<Task> tasksByIdDipendente) {
		this.tasksByIdDipendente = tasksByIdDipendente;
	}

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Dipendenti that = (Dipendenti) o;

        if (idDipendente != that.idDipendente) return false;
        if (!Objects.equals(nome, that.nome)) return false;
        if (!Objects.equals(cognome, that.cognome)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(ruolo, that.ruolo)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = idDipendente;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ruolo != null ? ruolo.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }

}
