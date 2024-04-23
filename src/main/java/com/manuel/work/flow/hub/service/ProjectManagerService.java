package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.ProjectManager;

public interface ProjectManagerService {

    ProjectManager login(String email, String password) throws RuntimeException;
    ProjectManager register(ProjectManager projectManager) throws RuntimeException;
    ProjectManager updateAccount(ProjectManager projectManager) throws RuntimeException;
    void deleteAccount(long idProjectManager);
    ProjectManager getProjectManager(int idProjectManager);

}
