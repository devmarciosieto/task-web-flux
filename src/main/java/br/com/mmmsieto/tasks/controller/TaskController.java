package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskResponseConverter;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.service.TaskService;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskResponseConverter converter;

    public TaskController(TaskService taskService, TaskResponseConverter converter) {
        this.taskService = taskService;
        this.converter = converter;
    }

    @GetMapping
    public Mono<List<TaskResponse>> getTasks() {
        return taskService.list()
                .map(converter::convertList);
    }

    @PostMapping
    public Mono<TaskResponse> createTask(@RequestBody Task task) {
        return taskService.insertTask(task)
                .map(converter::convert);
    }

}
