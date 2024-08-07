package by.ustsinovich.taskmanagementsystem.dto;

import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Schema(description = "Task data transfer object")
public class TaskDto {

    @Schema(description = "Unique identifier of the task", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank(message = "Title is required")
    @Size(max = 255, message = "Title must not exceed 255 chars")
    @Schema(description = "Title of the task")
    private String title;

    @NotBlank(message = "Description is required")
    @Size(max = 255, message = "Description must not exceed 255 chars")
    @Schema(description = "Description of the task")
    private String description;

    @NotBlank(message = "Priority is required")
    @Size(max = 255, message = "Priority must not exceed 255 chars")
    @Schema(description = "Priority of the task")
    private TaskPriority priority;

    @NotBlank(message = "Status is required")
    @Size(max = 255, message = "Status must not exceed 255 chars")
    @Schema(description = "Status of the task")
    private TaskStatus status;

    @Schema(description = "Date and time when the task was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the task was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @Schema(description = "User who initiated the task", accessMode = Schema.AccessMode.READ_ONLY)
    private UserDto initiator;

    @Schema(accessMode = Schema.AccessMode.READ_ONLY)
    private List<CommentDto> comments;

}