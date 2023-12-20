package br.com.mmmsieto.tasks.controller.converter;

import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskResponseConverter {

    public List<TaskResponse> convertList(List<Task> tasks) {
        return Optional.ofNullable(tasks)
                .map(t -> t.stream().map(this::convert).toList())
                .orElse(null);
    }

    public TaskResponse convert(Task task) {
        return Optional.ofNullable(task)
                .map(t -> {
                    TaskResponse response = new TaskResponse();
                    response.setTitle(t.getTitle());
                    response.setDescription(t.getDescription());
                    response.setPriority(t.getPriority());
                    response.setStatus(t.getStatus());
                    return response;
                })
                .orElse(null);
    }

    public Task convert(TaskResponse response) {
        return Optional.ofNullable(response)
                .map(r -> {
                    Task task = new Task.Builder()
                            .title(r.getTitle())
                            .description(r.getDescription())
                            .priority(r.getPriority())
                            .status(r.getStatus())
                            .build();
                    return task;
                })
                .orElse(null);
    }

}
