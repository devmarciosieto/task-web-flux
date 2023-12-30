package br.com.mmmsieto.tasks.utils;

import br.com.mmmsieto.tasks.controller.dtos.request.TaskRequest;
import br.com.mmmsieto.tasks.controller.dtos.response.TaskResponse;
import br.com.mmmsieto.tasks.model.Task;
import br.com.mmmsieto.tasks.model.enums.TaskSatatus;
import org.bson.types.ObjectId;

public class TestUtils {

    public static Task buildValidTask() {
        return  Task.builder()
                .id(ObjectId.get())
                .title("title")
                .description("description")
                .priority(1)
                .status(TaskSatatus.INSERT)
                .build();
    }

    public static TaskRequest createTaskRequest() {
        TaskRequest taskRequest = new TaskRequest();
        taskRequest.setTitle("title");
        taskRequest.setDescription("description");
        taskRequest.setPriority(1);
        return taskRequest;
    }

    public static TaskResponse createTaskResponse() {
        TaskResponse taskResponse = new TaskResponse();
        taskResponse.setId(ObjectId.get().toString());
        taskResponse.setTitle("title");
        taskResponse.setDescription("description");
        taskResponse.setPriority(1);
        taskResponse.setStatus(TaskSatatus.INSERT);
        return taskResponse;
    }

}
