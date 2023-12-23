package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskResponseConverter;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.service.TaskService;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;


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
    public Page<TaskResponse> getTasks(@ModelAttribute TaskFilter taskFilter,
                                        @PageableDefault(page = 0, size = 3,  sort = "title,asc")Pageable pageable) {
        return taskService.findPaginated(taskFilter, pageable)
                .map(converter::convert);
    }

    @PostMapping
    public Mono<TaskResponse> createTask(@RequestBody Task task) {
        return taskService.insertTask(task)
                .map(converter::convert);
    }

}
