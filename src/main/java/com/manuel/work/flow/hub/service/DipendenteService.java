package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.vo.DipendenteVO;

import java.util.List;

public interface DipendenteService {

    DipendenteVO login(String email, String password) throws RuntimeException;
    Dipendenti register(Dipendenti dipendente) throws RuntimeException;
    Dipendenti updateAccount(Dipendenti dipendente) throws RuntimeException;
    void deleteAccount(long idDipendente);
    List<DipendenteVO> findAllDipendenti();
    Dipendenti getDipendente(int idDipendente);
    DipendenteVO getDipendenteVO(int idDipendente);
}
