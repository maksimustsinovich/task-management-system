package by.ustsinovich.taskmanagementsystem.repository;

import by.ustsinovich.taskmanagementsystem.entity.Comment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment> findAll(Specification<Comment> specification, Pageable pageable);

    Page<Comment> findByTaskId(Long id, Specification<Comment> specification, Pageable pageable);

}
