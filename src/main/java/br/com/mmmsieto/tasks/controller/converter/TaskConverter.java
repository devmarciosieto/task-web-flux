package br.com.mmmsieto.tasks.controller.converter;

import br.com.mmmsieto.tasks.controller.dtos.request.TaskRequest;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TaskConverter {

    public List<TaskResponse> convertList(List<Task> tasks) {
        return Optional.ofNullable(tasks)
                .map(t -> t.stream().map(this::convert).toList())
                .orElse(null);
    }

    public TaskResponse convert(Task task) {
        return Optional.ofNullable(task)
                .map(t -> {
                    TaskResponse response = new TaskResponse();
                    response.setId(t.getId() != null ? t.getId().toString() : null);
                    response.setTitle(t.getTitle());
                    response.setDescription(t.getDescription());
                    response.setPriority(t.getPriority());
                    response.setStatus(t.getStatus());
                    return response;
                })
                .orElse(null);
    }

    public static Task convert(TaskRequest request) {
        return Optional.ofNullable(request)
                .map(r -> {
                    Task task = new Task.Builder()
                            .title(r.getTitle())
                            .description(r.getDescription())
                            .priority(r.getPriority())
                            .build();
                    return task;
                })
                .orElse(null);
    }

}
