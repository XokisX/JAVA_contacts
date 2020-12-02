package by.ermakovich.contacts.controller.entity;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ErrorResponse {
    public ErrorResponse(String message) {
        this.message = message;
    }

    private String message;
    private HttpStatus status;
}
