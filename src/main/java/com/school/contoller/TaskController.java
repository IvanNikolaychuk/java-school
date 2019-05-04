package com.school.contoller;

import com.school.domain.code.task.TaskService;
import com.school.domain.code.task.verification.VerificationSummary;
import com.school.domain.code.task.verification.VerificationService;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class TaskController {
    private final TaskService taskService = new TaskService();
    private final VerificationService verificationService = new VerificationService();

    @GetMapping("/task/{taskId}")
    public String greeting(@PathVariable String taskId, Model model) {
        model.addAttribute("task", taskService.get(taskId));
        return "task";
    }

    @PostMapping("task/verify/{taskId}")
    public ResponseEntity<String> verify(@PathVariable String taskId, @RequestParam String code) {
        try {
            VerificationSummary summary = verificationService.verify(taskService.get(taskId), code);
            return new ResponseEntity<>(new JSONObject(summary).toString(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ooops, something is wrong :(", INTERNAL_SERVER_ERROR);
        }
    }
}
