package com.uca.pncsegundoparcialgestiontareas.service;
import com.uca.pncsegundoparcialgestiontareas.dto.request.TaskDTORequest;
import com.uca.pncsegundoparcialgestiontareas.dto.response.TaskDTOResponse;
import com.uca.pncsegundoparcialgestiontareas.entities.Task;
import com.uca.pncsegundoparcialgestiontareas.exception.ResourceAlreadyExistsException;
import com.uca.pncsegundoparcialgestiontareas.exception.ResourceNotFoundException;
import com.uca.pncsegundoparcialgestiontareas.repository.TaskRepository;
import com.uca.pncsegundoparcialgestiontareas.utils.enums.Status;
import com.uca.pncsegundoparcialgestiontareas.utils.mappers.TaskMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    //Crear una nueva tarea
    public void createTask(TaskDTORequest task){
        if (taskRepository.existsByTitle(task.title().toLowerCase())){
            throw new ResourceAlreadyExistsException("Task with title " + task.title() + " already exists");
        }
        taskRepository.save(TaskMapper.toEntity(task));
    }

    //Listar todas las tareas (con filtros opcionales)
    public List<TaskDTOResponse> findAllTasks(){
        return taskRepository.findAll().stream()
                .map(TaskMapper::toResponse)
                .toList();
    }

    //Obtener una tarea por ID
    public TaskDTOResponse findTaskById(Long id){
        return TaskMapper.toResponse(taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task not found with id " + id)
        ));
    }

    //Actualizar la información de una tarea
    public void updateTask(Long id, TaskDTORequest task){
        Task taskToUpdate = TaskMapper.toEntity(task);
        if (taskRepository.existsById(id)){
            taskToUpdate.setId(id);
        }else{
            throw new ResourceNotFoundException("Task not found with id " + id);
        }
        taskRepository.save(taskToUpdate);
    }

    //Eliminar una tarea (respetando reglas de negocio)
    public void deleteTaskById(Long id){
        Task taskToDelete = taskRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Task not found with id " + id)
        );
        if (taskToDelete.getStatus().equals(Status.IN_PROGRESS) || taskToDelete.getStatus().equals(Status.REVIEW)){
            throw new IllegalStateException("Cannot delete tasks with IN_PROGRESS or REVIEW status");
        }
        taskRepository.deleteById(id);
    }
}
