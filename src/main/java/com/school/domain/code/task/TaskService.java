package com.school.domain.code.task;

import com.school.domain.code.task.repository.TaskRepository;

public class TaskService {
    private final TaskRepository taskRepository = new TaskRepository();

    public Task get(String taskId) {
        return taskRepository.get(taskId);
    }
}
