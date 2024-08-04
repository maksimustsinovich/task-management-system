package by.ustsinovich.taskmanagementsystem.filter;

import by.ustsinovich.taskmanagementsystem.enums.TaskPriority;
import by.ustsinovich.taskmanagementsystem.enums.TaskStatus;
import lombok.Data;

@Data
public class TaskFilter {

    private String title;

    private String description;

    private TaskStatus status;

    private TaskPriority taskPriority;

}
