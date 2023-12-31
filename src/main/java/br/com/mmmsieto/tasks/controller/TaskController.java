package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskConverter;
import br.com.mmmsieto.tasks.controller.dtos.request.TaskRequest;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.service.TaskService;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskController.class);
    private final TaskService taskService;

    private final TaskConverter converter;

    public TaskController(TaskService taskService, TaskConverter converter) {
        this.taskService = taskService;
        this.converter = converter;
    }

    // TODO: remover o retorno do list
    @GetMapping
    public List<TaskResponse> getTasks(@ModelAttribute TaskFilter taskFilter,
                                        @RequestParam(value = "page", defaultValue = "0") int page,
                                        @RequestParam(value = "size", defaultValue = "3") int size) {

        return taskService.findList(taskFilter, page, size)
                .stream().map(converter::convert).toList();
    }
    @PostMapping
    public Mono<TaskResponse> createTask(@RequestBody TaskRequest request) {
        return taskService.insertTask(converter.convert(request))
                .doOnNext(task -> LOGGER.info("Saved task with id {}", task.getId()))
                .map(converter::convert);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteTask(@PathVariable ObjectId id) {
        return Mono.just(id)
                .doOnNext(it -> LOGGER.info("Deleting task with id {}", id))
                .flatMap(taskService::deleteById);
    }

}
