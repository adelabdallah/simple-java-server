package com.adel.tasks.task;

import java.util.Objects;

public class Task {

    private String id;
    private String title;
    private String date;
    private String description;
    private boolean isCompleted;

    public Task(String id, String title, String date, String description, boolean isCompleted) {
        this.id = id;
        this.title = title;
        this.date = date;
        this.description = description;
        this.isCompleted = isCompleted;
    }

    /**
     * @return Task as valid JSON
     */
    @Override
    public String toString() {
        return "{" +
                "\"id\":\"" + id + "\"" +
                ", \"title\":\"" + title + "\"" +
                ", \"date\":\"" + date + "\"" +
                ", \"description\":\"" + description + "\"" +
                ", \"isCompleted\":\"" + isCompleted + "\"" +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        var task = (Task) o;
        return Objects.equals(id, task.id) &&
                Objects.equals(title, task.title) &&
                Objects.equals(date, task.date) &&
                Objects.equals(description, task.description) &&
                Objects.equals(isCompleted, task.isCompleted);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, date, description, isCompleted);
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public boolean getIsCompleted() {
        return isCompleted;
    }
}
