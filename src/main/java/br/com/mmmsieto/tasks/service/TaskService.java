package br.com.mmmsieto.tasks.service;

import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.repository.TaskRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Mono<Task> insertTask(Task task) {
        return Mono.just(task)
                .map(Task::insert)
                .flatMap(this::newTask);
    }

    public Mono<List<Task>> list() {
        return Mono.just(taskRepository.findAll());
    }

    private Mono<Task> newTask(Task task) {
        return Mono.just(task).map(taskRepository::save);
    }

}
