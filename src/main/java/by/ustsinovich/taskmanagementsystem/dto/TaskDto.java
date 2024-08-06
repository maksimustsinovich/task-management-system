package by.ustsinovich.taskmanagementsystem.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Schema(description = "Task data transfer object")
public class TaskDto {

    @Schema(description = "Unique identifier of the task", accessMode = Schema.AccessMode.READ_ONLY)
    private Long id;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Title of the task")
    private String title;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Description of the task")
    private String description;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Priority of the task")
    private String priority;

    @NotBlank
    @Size(max = 255)
    @Schema(description = "Status of the task")
    private String status;

    @Schema(description = "Date and time when the task was created", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime createdAt;

    @Schema(description = "Date and time when the task was last updated", accessMode = Schema.AccessMode.READ_ONLY)
    private LocalDateTime updatedAt;

    @Schema(description = "User who initiated the task")
    private UserDto initiator;

}