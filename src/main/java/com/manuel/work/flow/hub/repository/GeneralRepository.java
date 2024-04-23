package com.manuel.work.flow.hub.repository;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.entity.Task;

import java.io.Serializable;
import java.util.List;

public interface GeneralRepository<T, ID extends Serializable> {

    T findById(ID id);
    T findByEmail(String email);
    void save(T entity);
    void update(T entity);
    void delete(T entity);
    List<Dipendenti> findAllDipendenti();
    T findProjectByName(String nome);
    List<Progetti> findAllProgetti();
    List<Task> findAllTasks();

}
