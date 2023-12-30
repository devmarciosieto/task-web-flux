package br.com.mmmsieto.tasks.controller.dtos.response;

import br.com.mmmsieto.tasks.model.enums.TaskSatatus;

public class TaskResponse {

    private String id;
    private String title;
    private String description;
    private int priority;
    private TaskSatatus status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public TaskSatatus getStatus() {
        return status;
    }

    public void setStatus(TaskSatatus status) {
        this.status = status;
    }
}
