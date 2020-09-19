package com.adel.tasks.task;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class TaskRestController {

    private @Autowired
    TaskService taskService;

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

    @PostMapping("/update")
    public void updateTask(@RequestBody Map<String, Object> payload) {
        taskService.updateTask(
                payload.get("id").toString(),
                payload.get("title").toString(),
                payload.get("date").toString(),
                payload.get("description").toString(),
                payload.get("isCompleted").toString());
    }

    @DeleteMapping("/delete")
    public boolean deleteTask(@RequestParam(name = "id") String taskId) {
        return taskService.deleteTask(taskId);
    }

}
