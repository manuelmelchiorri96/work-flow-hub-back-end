package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.ProjectManager;
import com.manuel.work.flow.hub.exception.EmailAlreadyExistsException;
import com.manuel.work.flow.hub.exception.InvalidCredentialsException;
import com.manuel.work.flow.hub.repository.GeneralRepository;
import com.manuel.work.flow.hub.validation.ValidationUtils;

public class ProjectManagerServiceImpl implements ProjectManagerService {

    private final GeneralRepository<ProjectManager, Integer> generalRepository;

    public ProjectManagerServiceImpl(GeneralRepository<ProjectManager, Integer> generalRepository) {
        this.generalRepository = generalRepository;
    }

    @Override
    public ProjectManager login(String email, String password) throws RuntimeException {
        ProjectManager projectManager = generalRepository.findByEmail(email);

        if (projectManager == null || !projectManager.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Credenziali non valide. Controlla l'email e la password inserite.");
        }
        return projectManager;
    }

    @Override
    public ProjectManager register(ProjectManager projectManager) throws RuntimeException {
        ValidationUtils.validateFields(projectManager.getNome(), projectManager.getCognome(), projectManager.getEmail(), projectManager.getPassword(), projectManager.getRuolo(), "project-manager");

        if (generalRepository.findByEmail(projectManager.getEmail()) != null) {
            throw new EmailAlreadyExistsException("L'email inserita è già associata a un altro account.");
        }
        generalRepository.save(projectManager);
        return projectManager;
    }

    @Override
    public ProjectManager updateAccount(ProjectManager projectManager) throws RuntimeException {
        ValidationUtils.validateFields(projectManager.getNome(), projectManager.getCognome(), projectManager.getEmail(), projectManager.getPassword(), projectManager.getRuolo(), "project-manager");

        ProjectManager existingUser = generalRepository.findById(projectManager.getIdProjectManager());
        if (existingUser == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }

        generalRepository.update(projectManager);
        return projectManager;
    }

    @Override
    public void deleteAccount(long idProjectManager) {
        ProjectManager existingUser = generalRepository.findById((int) idProjectManager);
        if (existingUser == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }
        generalRepository.delete(existingUser);
    }

    @Override
    public ProjectManager getProjectManager(int idProjectManager) {
        ProjectManager userLogged = generalRepository.findById(idProjectManager);
        if (userLogged == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }
        return userLogged;
    }

}
