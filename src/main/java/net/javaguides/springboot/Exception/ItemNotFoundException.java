package net.javaguides.springboot.Exception;

import org.springframework.http.HttpStatus;


public class ItemNotFoundException extends CustomException {

    public ItemNotFoundException() {
        super(HttpStatus.NOT_FOUND, "Item Not Found");
    }

    public ItemNotFoundException(HttpStatus status, String message) {
        super(status, message);
    }
}
