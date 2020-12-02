package by.ermakovich.contacts.exception;

import by.ermakovich.contacts.controller.entity.ErrorResponse;
import by.ermakovich.contacts.controller.entity.ServerResponce;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ContactsExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ControllerException.class)
    public ServerResponce handleControllerException(
            ControllerException ex, WebRequest request) {
        return new ServerResponce(false,"Wrong data",null);
    }
}
