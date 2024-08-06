package by.ustsinovich.taskmanagementsystem.exception;

import java.io.Serial;

public class ResourceNotFoundException extends RuntimeException {

    @Serial
    private static final long serialVersionUID = 2362121219315524708L;

    public ResourceNotFoundException(String message) {
        super(message);
    }

}
