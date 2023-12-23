package br.com.mmmsieto.tasks.repository;

import br.com.mmmsieto.tasks.controller.filter.TaskFilter;
import br.com.mmmsieto.tasks.model.Task;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.support.PageableExecutionUtils;
import org.springframework.stereotype.Repository;


@Repository
public class TaskCustomRepository {

    private final MongoOperations mongoOperations;

    public TaskCustomRepository(MongoOperations mongoOperations) {
        this.mongoOperations = mongoOperations;
    }

    public Page<Task> findPaginated(TaskFilter filter, Pageable pageable) {

        Task task = new Task();

        task.setTitle(filter.getTitle());

        ExampleMatcher matcher = ExampleMatcher.matching()
            .withIgnorePaths("priority", "status");

        Example<Task> example = Example.of(task, matcher);

        Query query = Query.query(Criteria.byExample(example)).with(pageable);

        if (filter.getPriority() != 0) {
            query.addCriteria(Criteria.where("priority").is(filter.getPriority()));
        }

        if (filter.getStatus() != null) {
            query.addCriteria(Criteria.where("status").is(filter.getStatus()));
        }


        return PageableExecutionUtils.getPage(mongoOperations.find(query, Task.class), pageable,
                () -> mongoOperations.count(query, Task.class));
    }

}
