package net.javaguides.springboot.Exception;

import java.util.Locale;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import lombok.extern.slf4j.Slf4j;


@ControllerAdvice// allows you to handle exception across the whole application
@ResponseBody//tells a controller that the object returned is automatically serialized into JSON 
//and passed back into the HttpResponse object.
@Slf4j //It provides a simple abstraction of all the logging frameworks
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ CustomException.class })
    protected ResponseEntity<ErrorResponse> handleCustomException(
        CustomException ex,
        Locale locale
    ) {
        log.error(ex.getMessage(), ex);
      ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            ex.getLocalizedMessage(),
            ex.getStatus().value()
        );
        return new ResponseEntity<>(errorResponse, ex.getStatus());
    }

    @ExceptionHandler({ Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    protected ErrorResponse handleException(Exception ex, Locale locale) {
        log.error(ex.getMessage(), ex);
        ErrorResponse errorResponse = new ErrorResponse(
            ex.getMessage(),
            ex.getLocalizedMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        return errorResponse;
    }
}
