package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.exception.EmailAlreadyExistsException;
import com.manuel.work.flow.hub.exception.InvalidCredentialsException;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.repository.GeneralRepository;
import com.manuel.work.flow.hub.validation.ValidationUtils;
import com.manuel.work.flow.hub.vo.DipendenteVO;

import java.util.ArrayList;
import java.util.List;

public class DipendenteServiceImpl implements DipendenteService {

    private final GeneralRepository<Dipendenti, Integer> generalRepository;

    public DipendenteServiceImpl(GeneralRepository<Dipendenti, Integer> generalRepository) {
        this.generalRepository = generalRepository;
    }

    @Override
    public DipendenteVO login(String email, String password) throws RuntimeException {
        Dipendenti dipendente = generalRepository.findByEmail(email);
        DipendenteVO dipendenteVO = new DipendenteVO();

        if (dipendente == null || !dipendente.getPassword().equals(password)) {
            throw new InvalidCredentialsException("Credenziali non valide. Controlla l'email e la password inserite.");
        }else {
            dipendenteVO = ValidationUtils.convertToVO(dipendente);
        }
        return dipendenteVO;
    }

    @Override
    public Dipendenti register(Dipendenti dipendente) throws RuntimeException {
        ValidationUtils.validateFields(dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail(), dipendente.getPassword(), dipendente.getRuolo(), "sviluppatore");

        if (generalRepository.findByEmail(dipendente.getEmail()) != null) {
            throw new EmailAlreadyExistsException("L'email inserita è già associata a un altro account.");
        }
        generalRepository.save(dipendente);
        return dipendente;
    }

    @Override
    public Dipendenti updateAccount(Dipendenti dipendente) throws RuntimeException {
        ValidationUtils.validateFields(dipendente.getNome(), dipendente.getCognome(), dipendente.getEmail(), dipendente.getPassword(), dipendente.getRuolo(), "sviluppatore");

        Dipendenti existingUser = generalRepository.findById(dipendente.getIdDipendente());
        if (existingUser == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }

        generalRepository.update(dipendente);
        return dipendente;
    }

    @Override
    public void deleteAccount(long idDipendente) {
        Dipendenti existingUser = generalRepository.findById((int) idDipendente);
        if (existingUser == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }
        generalRepository.delete(existingUser);
    }

    @Override
    public List<DipendenteVO> findAllDipendenti() {
        List<Dipendenti> dipendenti = generalRepository.findAllDipendenti();
        List<DipendenteVO> dipendentiVO = new ArrayList<>();

        if(dipendenti.isEmpty()){
            throw new ListaVuotaException("Non ci sono dipendenti nel sistema");
        }

        for (Dipendenti dipendente : dipendenti) {
            dipendentiVO.add(ValidationUtils.convertToVO(dipendente));
        }
        return dipendentiVO;
    }

    @Override
    public Dipendenti getDipendente(int idDipendente) {
        Dipendenti userLogged = generalRepository.findById(idDipendente);
        if (userLogged == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }
        return userLogged;
    }

    @Override
    public DipendenteVO getDipendenteVO(int idDipendente) {
        Dipendenti dipendenteDaGestire = generalRepository.findById(idDipendente);
        DipendenteVO dipendenteDaGestireVO = new DipendenteVO();

        if (dipendenteDaGestire == null) {
            throw new IllegalArgumentException("L'utente specificato non esiste nel sistema.");
        }

        dipendenteDaGestireVO = ValidationUtils.convertToVO(dipendenteDaGestire);

        return dipendenteDaGestireVO;
    }

}
