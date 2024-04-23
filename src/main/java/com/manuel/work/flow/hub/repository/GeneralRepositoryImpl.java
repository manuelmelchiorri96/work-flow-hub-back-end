package com.manuel.work.flow.hub.repository;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.entity.ProjectManager;
import com.manuel.work.flow.hub.entity.Task;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.io.Serializable;
import java.util.List;

public class GeneralRepositoryImpl<T, ID extends Serializable> implements GeneralRepository<T, ID> {

    private final EntityManager entityManager;
    private final Class<T> entityClass;

    public GeneralRepositoryImpl(EntityManager entityManager, Class<T> entityClass) {
        this.entityManager = entityManager;
        this.entityClass = entityClass;
    }

    @Override
    public T findById(ID id) {
        return entityManager.find(entityClass, id);
    }

    @Override
    public T findByEmail(String email) {
        try {
            String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.email = :email";
            TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
            query.setParameter("email", email);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public void save(T entity) {
        entityManager.getTransaction().begin();
        entityManager.persist(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void update(T entity) {
        entityManager.getTransaction().begin();
        entityManager.merge(entity);
        entityManager.getTransaction().commit();
    }

    @Override
    public void delete(T entity) {
        entityManager.getTransaction().begin();

        if (entity instanceof Dipendenti) {

            Dipendenti dipendente = (Dipendenti) entity;
            List<Task> tasks = findAllTasksByDipendente(dipendente);

            for (Task task : tasks) {
                task.setDipendente(null);
            }
        }

        if (entity instanceof ProjectManager) {
            ProjectManager projectManager = (ProjectManager) entity;
            List<Progetti> projects = findAllProjectsByProjectManager(projectManager);

            for (Progetti project : projects) {
                project.setProjectManager(null);
            }
        }
        entityManager.remove(entity);
        entityManager.getTransaction().commit();
    }

    private List<Task> findAllTasksByDipendente(Dipendenti dipendente) {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t WHERE t.dipendente = :dipendente", Task.class);
        query.setParameter("dipendente", dipendente);
        return query.getResultList();
    }

    private List<Progetti> findAllProjectsByProjectManager(ProjectManager projectManager) {
        TypedQuery<Progetti> query = entityManager.createQuery("SELECT p FROM Progetti p WHERE p.projectManager = :projectManager", Progetti.class);
        query.setParameter("projectManager", projectManager);
        return query.getResultList();
    }


    @Override
    public List<Dipendenti> findAllDipendenti() {
        TypedQuery<Dipendenti> query = entityManager.createQuery("SELECT d FROM Dipendenti d", Dipendenti.class);
        return query.getResultList();
    }

    @Override
    public T findProjectByName(String nome) {
        try {
            String queryString = "SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.nome = :nome";
            TypedQuery<T> query = entityManager.createQuery(queryString, entityClass);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public List<Progetti> findAllProgetti() {
        TypedQuery<Progetti> query = entityManager.createQuery("SELECT p FROM Progetti p", Progetti.class);
        return query.getResultList();
    }

    @Override
    public List<Task> findAllTasks() {
        TypedQuery<Task> query = entityManager.createQuery("SELECT t FROM Task t", Task.class);
        return query.getResultList();
    }

}
