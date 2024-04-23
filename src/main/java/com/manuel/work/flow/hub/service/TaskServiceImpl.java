package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Dipendenti;
import com.manuel.work.flow.hub.entity.Task;
import com.manuel.work.flow.hub.exception.ListaVuotaException;
import com.manuel.work.flow.hub.repository.GeneralRepository;
import com.manuel.work.flow.hub.validation.ValidationUtils;
import com.manuel.work.flow.hub.vo.TaskVO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskServiceImpl implements TaskService {

    private final GeneralRepository<Task, Integer> generalRepository;

    public TaskServiceImpl(GeneralRepository<Task, Integer> generalRepository) {
        this.generalRepository = generalRepository;
    }

    @Override
    public Task saveTask(Task task) {
        ValidationUtils.validateFields(task.getDescrizione(), task.getDataInizio(), task.getDataFinePrevista(), task.getStatoTask(), task.getProgetto(), task.getDipendente());

        if (generalRepository.findById(task.getIdTask()) != null) {
            throw new IllegalArgumentException("Il task già è esistente nel sistema.");
        }

        generalRepository.save(task);
        return task;
    }

    @Override
    public Task updateTask(Task task) {
        ValidationUtils.validateFields(task.getDescrizione(), task.getDataInizio(), task.getDataFinePrevista(), task.getStatoTask(), task.getProgetto(), task.getDipendente());

        Task existingTask = generalRepository.findById(task.getIdTask());

        if (existingTask == null) {
            throw new IllegalArgumentException("Il task specificato non esiste nel sistema.");
        }

        if ("100%".equals(existingTask.getStatoTask())) {
            task.setStatoTask("Task completato");
        }

        if (!task.equals(existingTask)) {
            generalRepository.update(task);
        }

        return task;
    }


    @Override
    public List<TaskVO> findAllTask() {
        List<Task> tasks = generalRepository.findAllTasks();
        List<TaskVO> tasksVO = new ArrayList<>();

        if (tasks.isEmpty()) {
            throw new ListaVuotaException("Non ci sono task nel sistema");
        }

        for (Task task : tasks) {
            if (task.getDipendente() == null) {
                task.setDipendente(new Dipendenti());
            }

            tasksVO.add(ValidationUtils.convertToVO(task));
        }

        return tasksVO;
    }


    @Override
    public List<TaskVO> findTaskByDipendente(int idDipendente) {
        List<Task> tasks = generalRepository.findAllTasks();
        List<TaskVO> tasksVO = new ArrayList<>();

        List<Task> tasksByDipendente = tasks.stream()
                .filter(task -> task.getDipendente() != null && task.getDipendente().getIdDipendente() == idDipendente)
                .collect(Collectors.toList());

        if (tasksByDipendente.isEmpty()) {
            throw new ListaVuotaException("Non ci sono task nel sistema associati al dipendente.");
        }

        for (Task task : tasksByDipendente) {
            tasksVO.add(ValidationUtils.convertToVO(task));
        }

        return tasksVO;
    }


    @Override
    public List<TaskVO> findTaskByProgetto(int idProgetto) {
        List<Task> tasks = generalRepository.findAllTasks();
        List<TaskVO> tasksVO = new ArrayList<>();

        List<Task> tasksByProgetto = tasks.stream()
                .filter(task -> task.getProgetto() != null && task.getProgetto().getIdProgetto() == idProgetto)
                .collect(Collectors.toList());

        if (tasksByProgetto.isEmpty()) {
            throw new ListaVuotaException("Non ci sono task nel sistema associati a questo progetto.");
        }

        for (Task task : tasksByProgetto) {
            tasksVO.add(ValidationUtils.convertToVO(task));
        }

        return tasksVO;
    }


}

