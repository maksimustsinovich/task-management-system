package by.ustsinovich.taskmanagementsystem.exception;

import java.io.Serial;

public class CommentNotFoundException extends ResourceNotFoundException {

    @Serial
    private static final long serialVersionUID = -6268349500831603734L;

    public CommentNotFoundException(Long id) {
        super("Comment with ID `%d` not found".formatted(id));
    }

}
