package com.adel.tasks.task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class TaskService {

    @Autowired
    private TaskDao taskDao;

    private static final Logger logger = LoggerFactory.getLogger(TaskService.class);

    public void createTask(String id, String title, String date, String description) {
        logger.info("Adding task to database...");
        var task = new Task(id, title, date, description, false);
        taskDao.createTask(task);
    }

    public void updateTask(String id, String title, String date, String description, String isCompleted) {
        logger.info("Updating task...");
        var task = new Task(id, title, date, description, Boolean.parseBoolean(isCompleted));
        taskDao.updateTask(task);
    }

    public ArrayList<String> getTasks() {
        return taskDao.getTasks();
    }

    public boolean deleteTask(String taskId) {
        return taskDao.deleteTask(taskId);
    }
}
