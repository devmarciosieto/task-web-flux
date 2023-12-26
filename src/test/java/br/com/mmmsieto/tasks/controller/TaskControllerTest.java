package br.com.mmmsieto.tasks.controller;

import br.com.mmmsieto.tasks.controller.converter.TaskConverter;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.service.TaskService;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskControllerTest {

    @InjectMocks
    private TaskController taskController;

    @Mock
    private TaskService taskService;

    @Mock
    private TaskConverter converter;

    private WebTestClient webTestClient;

    @BeforeEach
    void setUp() {
        this.webTestClient = WebTestClient.bindToController(taskController).build();
    }

    // TODO: remover o retorno do list
    @Test
    void controller_muskReturnOk_whenGetPaginatedSuccessFully() {

        List<Task> mockPage = List.of(new Task());
        Mockito.when(taskService.findList(Mockito.any(), Mockito.anyInt(), Mockito.anyInt()))
                .thenReturn(mockPage);

        this.webTestClient.get()
                .uri("/tasks")
                .exchange()
                .expectStatus().isOk()
                .expectBody(List.class);
    }

    @Test
    void controller_muskReturnOk_whenSaveSuccessfully() {

        when(converter.convert(any(Task.class))).thenReturn(new TaskResponse());
        when(taskService.insertTask(any())).thenReturn(Mono.just(new Task()));

        this.webTestClient.post()
                .uri("/tasks")
                .bodyValue(new Task())
                .exchange()
                .expectStatus().isOk()
                .expectBody(TaskResponse.class);
    }

    @Test
    void controller_muskReturnOk_whenDeleteSuccessfully() {

        ObjectId fakeId = new ObjectId();
        Mockito.when(taskService.deleteById(fakeId)).thenReturn(Mono.empty());

        this.webTestClient.delete()
                .uri("/tasks/" + fakeId.toString())
                .exchange()
                .expectStatus()
                .isNoContent();
    }

}