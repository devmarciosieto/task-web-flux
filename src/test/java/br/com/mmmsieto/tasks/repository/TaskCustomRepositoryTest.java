package br.com.mmmsieto.tasks.repository;

import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.mongodb.core.MongoOperations;

import java.util.List;

import static br.com.mmmsieto.tasks.utils.TestUtils.buildValidTask;
import static br.com.mmmsieto.tasks.utils.TestUtils.createTaskFilter;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest
class TaskCustomRepositoryTest {

    @InjectMocks
    private TaskCustomRepository taskCustomRepository;

    @Mock
    private MongoOperations mongoOperations;

    @Test
    void customRepository_mustReturnPageWhitOneElement_whenSendTask() {
        Task task = buildValidTask();

        TaskFilter filter = createTaskFilter();

        when(mongoOperations.find(any(), any())).thenReturn(List.of(task));
        Page<Task> page = taskCustomRepository.findPaginated(filter, 0, 1);

        assertNotNull(page);
        assertEquals(1, page.getNumberOfElements());
        assertEquals(1, page.getTotalPages());
        assertEquals(1, page.getContent().size());
        assertEquals(task, page.getContent().get(0));

    }

}