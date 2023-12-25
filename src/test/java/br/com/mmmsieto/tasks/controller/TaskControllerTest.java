package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskConverter;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.service.TaskService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

@SpringBootTest
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskConverter converter;


    @Test
    void getTasks() {
    }

    @Test
    void controller_muskReturnOk_whenSaveSuccessFully() {

        Mockito.when(converter.convert(Mockito.any(Task.class))).thenReturn(new TaskResponse());
        Mockito.when(taskService.insertTask(Mockito.any())).thenReturn(Mono.just(new Task()));

        WebTestClient  webTestClient = WebTestClient.bindToController(taskController).build();

        webTestClient.post()
                .uri("/tasks")
                .bodyValue(new Task())
                .exchange()
                .expectStatus().isOk();

    }

    @Test
    void deleteTask() {
    }
}