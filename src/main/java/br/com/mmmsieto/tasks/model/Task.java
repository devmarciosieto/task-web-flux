package br.com.mmmsieto.tasks.model;

import br.com.mmmsieto.tasks.model.enums.TaskSatatus;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "task")
public class Task {

    @Id
    private ObjectId id;

    private String title;

    private String description;

    private int priority;

    private TaskSatatus status;

    public Task() {
    }


    public Task(String title, String description, int priority, TaskSatatus status) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
    }

    public Task(Builder builder) {
        this.title = builder.title;
        this.description = builder.description;
        this.priority = builder.priority;
        this.status = builder.status;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(Task task) {
        return new Builder(task);
    }


    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
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

    public Task insert() {
        return builder(this)
                .status(TaskSatatus.INSERT)
                .build();
    }

    public static class Builder {

        private ObjectId id;
        private String title;
        private String description;
        private int priority;
        private TaskSatatus status;

        public Builder() {
        }

        public Builder(Task task) {
            this.title = task.getTitle();
            this.description = task.getDescription();
            this.priority = task.getPriority();
            this.status = task.getStatus();
        }

        public Builder id(ObjectId id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder priority(int priority) {
            this.priority = priority;
            return this;
        }

        public Builder status(TaskSatatus status) {
            this.status = status;
            return this;
        }

        public Task build() {
            return new Task(this);
        }

    }

}
