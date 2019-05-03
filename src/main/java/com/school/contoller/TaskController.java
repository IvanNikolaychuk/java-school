package com.school.contoller;

import com.school.app.CodeLoader;
import com.school.domain.code.rest.Class;
import org.json.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.OK;

@Controller
public class TaskController {

    @GetMapping("/task/{taskId}")
    public String greeting(@PathVariable String taskId, Model model) {
        model.addAttribute("taskTitle", "Hello Word!");
        return "task";
    }

    @RequestMapping(value = "/task/code")
    public @ResponseBody ResponseEntity<String> loadCode(@RequestParam String taskId) {
        try {
            Class aClass = new CodeLoader().load(taskId);
            return new ResponseEntity<>(new JSONObject(aClass).toString(), OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Ooops, something is wrong :(", INTERNAL_SERVER_ERROR);
        }
    }
}
