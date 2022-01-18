package net.javaguides.springboot.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;


public class CustomException extends ResponseStatusException {

    public CustomException() {
        super(HttpStatus.BAD_REQUEST, "Bad Request");
    }

    public CustomException(HttpStatus status, String message) {
        super(status, message);
    }
}
