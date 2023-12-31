package br.com.mmmsieto.tasks.service;

import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.repository.TaskCustomRepository;
import br.com.mmmsieto.tasks.repository.TaskRepository;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class TaskService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TaskService.class);
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
                .flatMap(this::newTask)
//                .flatMap(it -> doError())
                .doOnError(error -> LOGGER.error("Error during save task. Title: {}", task.getTitle(), error));
            //    .onErrorResume(it -> Mono.just(Task.builder().title("Error").build()));
    }

//    public Mono<Task> doError() {
//        return Mono.error(new RuntimeException("Error during save task"));
//    }

    public Mono<List<Task>> list() {
        return Mono.just(taskRepository.findAll());
    }

    private Mono<Task> newTask(Task task) {
        return Mono.just(task)
                .doOnNext(t -> LOGGER.info("Saving task with title {}", t.getTitle()))
                .map(taskRepository::save);
    }

    public List<Task> findList(TaskFilter taskFilter, int page, int size) {
        return taskCustomRepository.findPaginated(taskFilter, page, size).stream().toList();
    }

    public Mono<Void> deleteById(ObjectId id) {
        return Mono.fromRunnable(() -> taskRepository.deleteById(id));
    }
}
