package net.javaguides.springboot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

/**
 * CustomException
 */
public class CustomException extends ResponseStatusException {

    public CustomException() {
        super(HttpStatus.NOT_FOUND, "Content Not Found");
    }

    public CustomException(HttpStatus status, String message) {
        super(status, message);
    }
}
