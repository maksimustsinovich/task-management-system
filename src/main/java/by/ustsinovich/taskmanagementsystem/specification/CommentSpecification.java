package by.ustsinovich.taskmanagementsystem.specification;

import by.ustsinovich.taskmanagementsystem.entity.Comment;
import by.ustsinovich.taskmanagementsystem.filter.CommentFilter;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class CommentSpecification {

    private CommentSpecification() {
        throw new IllegalStateException("Utility class");
    }

    public static Specification<Comment> filterBy(CommentFilter filter) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            // Filter by content
            if (filter.getContent() != null && !filter.getContent().isEmpty()) {
                predicates.add(criteriaBuilder.like(root.get("content"), "%" + filter.getContent() + "%"));
            }

            // Filter by task id
            if (filter.getTaskId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("task").get("id"), filter.getTaskId()));
            }

            // Filter by author id
            if (filter.getAuthorId() != null) {
                predicates.add(criteriaBuilder.equal(root.get("author").get("id"), filter.getAuthorId()));
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
