package by.ustsinovich.taskmanagementsystem.filter;

import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Filter for tasks")
public class TaskFilter {

    @Schema(description = "Task title")
    private String title;

    @Schema(description = "Task description")
    private String description;

    @Schema(description = "Task status")
    private TaskStatus status;

    @Schema(description = "Task priority")
    private TaskPriority taskPriority;

    @Schema(description = "Task initiator ID")
    private Long initiatorId;

    @Schema(description = "Task executor ID")
    private Long executorId;

}
