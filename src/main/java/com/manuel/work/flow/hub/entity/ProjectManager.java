package com.manuel.work.flow.hub.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Objects;


@Entity
public class ProjectManager implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "ID_ProjectManager")
    private int idProjectManager;

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

    public ProjectManager() {
	}
    

    public ProjectManager(int idProjectManager, String nome, String cognome, String email, String ruolo,
			String password) {
		super();
		this.idProjectManager = idProjectManager;
		this.nome = nome;
		this.cognome = cognome;
		this.email = email;
		this.ruolo = ruolo;
		this.password = password;
	}

	public int getIdProjectManager() {
		return idProjectManager;
	}

	public void setIdProjectManager(int idProjectManager) {
		this.idProjectManager = idProjectManager;
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

	@Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProjectManager that = (ProjectManager) o;

        if (idProjectManager != that.idProjectManager) return false;
        if (!Objects.equals(nome, that.nome)) return false;
        if (!Objects.equals(cognome, that.cognome)) return false;
        if (!Objects.equals(email, that.email)) return false;
        if (!Objects.equals(ruolo, that.ruolo)) return false;
        return Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        int result = idProjectManager;
        result = 31 * result + (nome != null ? nome.hashCode() : 0);
        result = 31 * result + (cognome != null ? cognome.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (ruolo != null ? ruolo.hashCode() : 0);
        result = 31 * result + (password != null ? password.hashCode() : 0);
        return result;
    }
}
