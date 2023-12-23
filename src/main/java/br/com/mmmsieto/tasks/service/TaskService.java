package br.com.mmmsieto.tasks.service;

import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.repository.TaskCustomRepository;
import br.com.mmmsieto.tasks.repository.TaskRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskCustomRepository taskCustomRepository;

    public TaskService(TaskRepository taskRepository,
                        TaskCustomRepository taskCustomRepository) {
        this.taskRepository = taskRepository;
        this.taskCustomRepository = taskCustomRepository;
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


    public Page<Task> findPaginated(TaskFilter taskFilter, Pageable pageable) {
        return taskCustomRepository.findPaginated(taskFilter, pageable);
    }
}
