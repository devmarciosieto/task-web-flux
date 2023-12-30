package br.com.mmmsieto.tasks.controller.converter;

import br.com.mmmsieto.tasks.controller.dtos.request.TaskRequest;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static br.com.mmmsieto.tasks.utils.TestUtils.buildValidTask;
import static br.com.mmmsieto.tasks.utils.TestUtils.createTaskRequest;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TaskConverterTest {

    @InjectMocks
    private TaskConverter taskConverter;

    @Test
    void converter_mustReturnTaskResponse_whenInputTask() {

        Task task = buildValidTask();

        TaskResponse response = taskConverter.convert(task);

        assertNotNull(response);
        assertEquals(task.getId().toString(), response.getId());
        assertEquals(task.getTitle(), response.getTitle());
        assertEquals(task.getDescription(), response.getDescription());
        assertEquals(task.getPriority(), response.getPriority());
        assertEquals(task.getStatus(), response.getStatus());

    }

    @Test
    void converter_mustReturnTask_whenInputTaskRequest() {

        TaskRequest taskRequest = createTaskRequest();

        Task task = taskConverter.convert(taskRequest);

        assertNotNull(task);
        assertEquals(taskRequest.getTitle(), task.getTitle());
        assertEquals(taskRequest.getDescription(), task.getDescription());
        assertEquals(taskRequest.getPriority(), task.getPriority());

    }

    @Test
    void converter_mustReturnListTaskResponse_whenInputListTask() {

        List<Task> tasks = List.of(buildValidTask());

        List<TaskResponse> taskResponses = taskConverter.convertList(tasks);

        assertNotNull(taskResponses);
        assertEquals(tasks.get(0).getTitle(), taskResponses.get(0).getTitle());
        assertEquals(tasks.get(0).getDescription(), taskResponses.get(0).getDescription());
        assertEquals(tasks.get(0).getPriority(), taskResponses.get(0).getPriority());

    }


}