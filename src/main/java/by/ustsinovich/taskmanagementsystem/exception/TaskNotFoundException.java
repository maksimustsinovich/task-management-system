package by.ustsinovich.taskmanagementsystem.exception;

import java.io.Serial;

public class TaskNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = -5667749749783969197L;

    public TaskNotFoundException(Long id) {
        super("Task with ID `%d` not found".formatted(id));
    }

}
