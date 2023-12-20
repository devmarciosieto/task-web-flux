package br.com.mmmsieto.tasks.service;

import br.com.mmmsieto.tasks.model.Task;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {

    public static List<Task> taskList = new ArrayList<>();

    public Mono<Task> insertTask(Task task) {
        return Mono.just(task)
                .map(Task::insert)
                .flatMap(this::newTask);
    }

    public Mono<List<Task>> list() {
        return Mono.just(taskList);
    }

    private Mono<Task> newTask(Task task) {
        return Mono.just(task).map(Task::newTask);
    }

}
