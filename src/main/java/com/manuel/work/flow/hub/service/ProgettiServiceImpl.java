package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Progetti;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.repository.GeneralRepository;
import com.manuel.work.flow.hub.validation.ValidationUtils;
import com.manuel.work.flow.hub.vo.ProgettoVO;

import java.util.ArrayList;
import java.util.List;

public class ProgettiServiceImpl implements ProgettiService {

    private final GeneralRepository<Progetti, Integer> generalRepository;

    public ProgettiServiceImpl(GeneralRepository<Progetti, Integer> generalRepository) {
        this.generalRepository = generalRepository;
    }


    @Override
    public Progetti saveProject(Progetti progetto) {
        ValidationUtils.validateFields(progetto.getNome(), progetto.getDescrizione(), progetto.getStatoProgetto(), progetto.getDataInizio(), progetto.getDataFinePrevista(), progetto.getProjectManager());
        Progetti progettoExisting = generalRepository.findProjectByName(progetto.getNome());

        if (progettoExisting != null){
            throw new IllegalArgumentException("Progetto già esistente nel sistema");
        }

        generalRepository.save(progetto);
        return progetto;
    }


    @Override
    public Progetti updateProject(Progetti progetto) {
        ValidationUtils.validateFields(progetto.getNome(), progetto.getDescrizione(), progetto.getStatoProgetto(), progetto.getDataInizio(), progetto.getDataFinePrevista(), progetto.getProjectManager());
        Progetti existingProject = generalRepository.findProjectByName(progetto.getNome());

        if (existingProject == null) {
            throw new IllegalArgumentException("Il progetto specificato non esiste nel sistema.");
        }
        generalRepository.update(progetto);
        return progetto;
    }

    @Override
    public void deleteProject(long idProject) {
        Progetti existingProject = generalRepository.findById((int) idProject);

        if (existingProject == null) {
            throw new IllegalArgumentException("Il progetto specificato non esiste nel sistema.");
        }
        generalRepository.delete(existingProject);
    }

    @Override
    public List<ProgettoVO> findAllProgetti() {
        List<Progetti> progetti = generalRepository.findAllProgetti();
        List<ProgettoVO> progettiVO = new ArrayList<>();

        if (progetti.isEmpty()) {
            throw new ListaVuotaException("Non ci sono progetti nel sistema");
        }
        for(Progetti progetto: progetti) {
            progettiVO.add(ValidationUtils.convertToVO(progetto));
        }

        return progettiVO;
    }

}
