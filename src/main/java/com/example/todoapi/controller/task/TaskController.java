package com.example.todoapi.controller.task;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.example.todoapi.model.PageDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.example.todoapi.controller.TasksApi;
import com.example.todoapi.model.TaskDTO;
import com.example.todoapi.model.TaskForm;
import com.example.todoapi.model.TaskListDTO;
import com.example.todoapi.service.task.TaskEntity;
import com.example.todoapi.service.task.TaskService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class TaskController  implements TasksApi{

    private final TaskService taskService;

    @Override
    public ResponseEntity<TaskDTO> showTask(Long taskId) {
        var entity = taskService.find(taskId);
        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        return ResponseEntity.ok(dto);
    }

    @Override
    public ResponseEntity<TaskDTO> createTask(TaskForm form){
        var entity = taskService.create(form.getTitle());
        var dto = new TaskDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());

        return ResponseEntity
        .created(URI.create("/tasks/" + dto.getId()))
        .body(dto);
    }

    @Override
    public ResponseEntity<TaskListDTO> listTasks(Integer limit,Long offset) {
        var entityList = taskService.find(limit,offset);
        var dtoList = entityList.stream()
            .map(TaskController::getTaskDTO)
            .collect(Collectors.toList());
        var dto = new TaskListDTO();
        var pageDTO = new PageDTO();
        pageDTO.setLimit(limit);
        pageDTO.setOffset(offset);
        pageDTO.setSize(dtoList.size());

        dto.setResults(dtoList);
        dto.setPage(pageDTO);

        return ResponseEntity.ok(dto);
    }

    private static TaskDTO getTaskDTO(TaskEntity taskEnttiy) {
        var taskDTO = new TaskDTO();
        taskDTO.setId(taskEnttiy.getId());
        taskDTO.setTitle(taskEnttiy.getTitle());
        return taskDTO;
    }
}
