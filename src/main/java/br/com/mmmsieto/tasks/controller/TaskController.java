package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskConverter;
import br.com.mmmsieto.tasks.controller.dtos.request.TaskRequest;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.service.TaskService;
import org.bson.types.ObjectId;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import org.springframework.data.domain.Pageable;


@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskConverter converter;

    public TaskController(TaskService taskService, TaskConverter converter) {
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
    public Mono<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return taskService.insertTask(converter.convert(request))
                .map(converter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable ObjectId id) {
        return Mono.just(id)
                .flatMap(taskService::deleteById);
    }

}
