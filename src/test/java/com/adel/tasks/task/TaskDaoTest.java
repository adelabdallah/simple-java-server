package com.adel.tasks.task;

import com.mongodb.client.*;
import org.bson.Document;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
class TaskDaoTest {

    @Mock
    private MongoClient mockClient;
    @Mock
    private MongoDatabase mockDb;
    @Mock
    private MongoCollection mockCollection;
    @Mock
    private FindIterable<Document> mockIterable;
    @Mock
    private MongoCursor mockCursor;
    @InjectMocks
    private TaskDao taskDao = new TaskDao();

    private Task testTask = new Task(
            "someId",
            "someTitle",
            "someDate",
            "someDescription",
            false);

    private Document testDocument = new Document("id", testTask.getId())
            .append("title", testTask.getTitle())
            .append("date", testTask.getDate())
            .append("description", testTask.getDescription())
            .append("isCompleted", testTask.getIsCompleted());

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        when(mockClient.getDatabase(Mockito.anyString())).thenReturn(mockDb);
        when(mockDb.getCollection(Mockito.anyString())).thenReturn(mockCollection);
        when(mockCollection.find()).thenReturn(mockIterable);
        when(mockIterable.iterator()).thenReturn(mockCursor);
        when(mockCursor.hasNext()).thenReturn(true).thenReturn(false);
        when(mockCursor.next()).thenReturn(testDocument);
    }

    @Test
    void createTask() {
        assertTrue(taskDao.createTask(testTask));
    }

    @Test
    void getTasks() {
        ArrayList<String> tasks = taskDao.getTasks();
        assertEquals(1, tasks.size());
    }

    @Test
    void deleteTask() {
        assertTrue(taskDao.deleteTask("someId"));
    }
}