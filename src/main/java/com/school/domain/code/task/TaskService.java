package com.school.domain.code.task;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class TaskService {
    private final Map<String, Task> tasks;

    public TaskService() {
        tasks = new HashMap<>();

        tasks.put("task01", new Task("task01",
                "Hello World",
                Arrays.asList(new Class("package javaschool.task.section01.task01;\n" +
                        "\n" +
                        "public class Main {\n" +
                        "    public static void main(String[] args) {\n" +
                        "\n" +
                        "    }\n" +
                        "}", "Main")),
                Arrays.asList(
                        "Program should compile",
                        "Program should output \"Hello world\""
                )));
    }

    public Task load(String taskId) {
        return tasks.get(taskId);
    }
}
