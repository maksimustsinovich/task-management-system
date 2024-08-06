package by.ustsinovich.taskmanagementsystem.entity;

import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Task implements Serializable {

    @Serial
    private static final long serialVersionUID = 7403960094524985393L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority priority;

    @OneToMany(mappedBy = "task")
    private List<Comment> comments;

    @ManyToOne
    @JoinColumn(name = "initiator_id", referencedColumnName = "id")
    private User initiator;

    @ManyToMany(mappedBy = "executed")
    private List<User> executors;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id.equals(task.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

}
