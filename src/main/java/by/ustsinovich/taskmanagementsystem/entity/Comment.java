package by.ustsinovich.taskmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Comment implements Serializable {

    @Serial
    private static final long serialVersionUID = -8490605189527377567L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @CreationTimestamp
    private LocalDateTime createdAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;

    private String content;

    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User author;

}
