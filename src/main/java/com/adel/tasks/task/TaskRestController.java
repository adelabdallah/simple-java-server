package com.adel.tasks.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@RestController
public class TaskRestController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/get")
    public ArrayList<String> tasks() {
        return taskService.getTasks();
    }

    @PostMapping("/create")
    public void createTask(@RequestBody Map<String, Object> payload) {

        taskService.createTask(
                payload.get("id").toString(),
                payload.get("title").toString(),
                payload.get("date").toString(),
                payload.get("description").toString());
    }

    @DeleteMapping("/delete")
    public boolean deleteTask(@RequestParam(name = "id") String taskId) {
        return taskService.deleteTask(taskId);
    }

}
