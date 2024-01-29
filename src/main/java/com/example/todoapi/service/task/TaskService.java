package com.example.todoapi.service.task;


import org.springframework.stereotype.Service;

import com.example.todoapi.repository.task.TaskRecord;
import com.example.todoapi.repository.task.TaskRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository TaskRepository;

    public TaskEntity find(long taskId) {
        return TaskRepository.select(taskId)
        .map(record -> new TaskEntity(record.getId(), record.getTitle()))
        .orElseThrow(() -> new TaskEntityNotFoundException(taskId));
    }

    public TaskEntity create(String title) {
        var record = new TaskRecord(null,title);
        TaskRepository.insert(record);
        return new TaskEntity(record.getId(),record.getTitle());
    }
}
