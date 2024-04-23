package com.manuel.work.flow.hub.service;

import com.manuel.work.flow.hub.entity.Task;
import com.manuel.work.flow.hub.vo.TaskVO;

import java.util.List;

public interface TaskService {
    Task saveTask(Task task);
    Task updateTask(Task task);
    List<TaskVO> findAllTask();
    List<TaskVO> findTaskByDipendente(int idDipendente);
    List<TaskVO> findTaskByProgetto(int idProgetto);
}
