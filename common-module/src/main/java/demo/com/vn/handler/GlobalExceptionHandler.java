package demo.com.vn.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    // Handle generic exceptions
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception ex) {
        // You can log ex here

        // Return custom message and 500 status
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body("Something went wrong: " + ex.getMessage());
    }
}
