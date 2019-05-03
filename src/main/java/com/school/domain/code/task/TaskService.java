package com.school.domain.code.task;

import com.school.domain.code.task.repository.TaskRepository;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public Task load(String taskId) {
        return taskRepository.get(taskId);
    }
}
