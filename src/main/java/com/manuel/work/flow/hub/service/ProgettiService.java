package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.vo.ProgettoVO;

import java.util.List;

public interface ProgettiService {

    Progetti saveProject(Progetti progetto);
    Progetti updateProject(Progetti progetto);
    void deleteProject(long idProject);
    List<ProgettoVO> findAllProgetti();

}
