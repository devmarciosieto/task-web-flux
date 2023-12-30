package br.com.mmmsieto.tasks.service;

import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.repository.TaskCustomRepository;
import br.com.mmmsieto.tasks.repository.TaskRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import reactor.test.StepVerifier;

import java.util.List;

import static br.com.mmmsieto.tasks.utils.TestUtils.buildValidTask;
import static br.com.mmmsieto.tasks.utils.TestUtils.createTaskFilter;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


@SpringBootTest
class TaskServiceTest {

    @InjectMocks
    private TaskService taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskCustomRepository taskCustomRepository;

    @Test
    void service_mustReturnTask_whenInsertSuccessfully() {
        Task task = buildValidTask();

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        StepVerifier.create(taskService.insertTask(task))
                .then(() -> verify(taskRepository, Mockito.times(1)).save(any(Task.class)))
                .expectNext(task)
                .expectComplete();
    }

    @Test
    void service_mustReturnVoid_whenDeleteSuccessfully() {
        Task task = buildValidTask();

        when(taskRepository.save(any(Task.class))).thenReturn(task);

        StepVerifier.create(taskService.insertTask(task))
                .then(() -> verify(taskRepository, Mockito.times(1)).save(any(Task.class)))
                .expectNext(task)
                .expectComplete();
    }

    @Test
    void service_mustReturnList_whenFindAllSuccessfully() {

        TaskFilter taskFilter = createTaskFilter();
        Page<Task> page = Mockito.mock(Page.class);

        when(taskCustomRepository.findPaginated(any(TaskFilter.class), anyInt(), anyInt())).thenReturn(page);

        List<Task> result = taskService.findList(taskFilter, 0, 10);

        assertNotNull(result);
        verify(taskCustomRepository, Mockito.times(1)).findPaginated(any(TaskFilter.class), anyInt(), anyInt());

    }

}