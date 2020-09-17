package com.adel.tasks.task;

import com.mongodb.client.MongoClient;
import org.bson.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

import static com.mongodb.client.model.Filters.eq;

@Component
public class TaskDao {

    private @Autowired
    MongoClient mongo;

    private static final Logger logger = LoggerFactory.getLogger(TaskDao.class);

    public void createTask(Task task) {
        var document = createTaskDocument(task);
        try {
            var collection = mongo.getDatabase("local").getCollection("tasks");
            collection.insertOne(document);
            logger.info("Added task to database");
        } catch (Exception e) {
            logger.error("Unable to add task to DB");
            e.printStackTrace();
        }
    }

    public ArrayList<String> getTasks() {
        var tasks = new ArrayList<String>();
        var cursor = mongo.getDatabase("local").getCollection("tasks").find().iterator();

        try {
            while (cursor.hasNext()) {
                tasks.add(cursor.next().toJson());
            }
        } catch (Exception e) {
            logger.error("Unable to get tasks");
            e.printStackTrace();
        } finally {
            cursor.close();
        }
        return tasks;
    }

    public boolean updateTask(Task task) {
        try {
            var deleteSuccess = deleteTask(task.getId());
            if (deleteSuccess) createTask(task);
            return true;
        } catch (Exception e) {
            logger.error("Unable to update task");
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteTask(String taskId) {
        var filter = eq("id", taskId);

        try {
            logger.info("Deleting task...");
            mongo.getDatabase("local").getCollection("tasks").deleteOne(filter);
            return true;
        } catch (Exception e) {
            logger.error("Unable to delete task");
            e.printStackTrace();
            return false;
        }
    }

    private Document createTaskDocument(Task task) {
        return new Document("id", task.getId())
                .append("title", task.getTitle())
                .append("date", task.getDate())
                .append("description", task.getDescription())
                .append("isCompleted", task.getIsCompleted());
    }
}
