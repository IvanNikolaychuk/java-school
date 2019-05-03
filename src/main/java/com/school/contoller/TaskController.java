package com.school.contoller;

import com.school.domain.code.task.TaskService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TaskController {
    private final TaskService taskService = new TaskService();

    @GetMapping("/task/{taskId}")
    public String greeting(@PathVariable String taskId, Model model) {
        model.addAttribute("task", taskService.load(taskId));
        return "task";
    }
}
