package by.ustsinovich.taskmanagementsystem.specification;

import by.ustsinovich.taskmanagementsystem.entity.Task;
import by.ustsinovich.taskmanagementsystem.filter.TaskFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class TaskSpecification {

    private TaskSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Task> filterBy(TaskFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by title
            if (filter.getTitle() != null && !filter.getTitle().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%" + filter.getTitle() + "%"));
            }

            // Filter by description
            if (filter.getDescription() != null && !filter.getDescription().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("description"), "%" + filter.getDescription() + "%"));
            }

            // Filter by status
            if (filter.getStatus() != null) {
                predicates.add(criteriaBuilder.equal(root.get("status"), filter.getStatus()));
            }

            // Filter by priority
            if (filter.getTaskPriority() != null) {
                predicates.add(criteriaBuilder.equal(root.get("priority"), filter.getTaskPriority()));
            }

            // Filter by initiator
            if (filter.getInitiatorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("initiator").get("id"), filter.getInitiatorId()));
            }

            // Filter by executor
            if (filter.getExecutorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("executor").get("id"), filter.getExecutorId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }

}
