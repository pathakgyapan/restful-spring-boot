package net.javaguides.springboot.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * ErrorResponse
 */
@Getter
@Setter
@AllArgsConstructor
public class ErrorResponse {

    private String message;

    private String description;

    private int status;
}
