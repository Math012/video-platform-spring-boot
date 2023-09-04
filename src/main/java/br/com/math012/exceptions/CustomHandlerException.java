package br.com.math012.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class CustomHandlerException extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StructCustomException> handlerNotFoundException(Exception e, WebRequest request){
        var exception = new StructCustomException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ExistingObjectException.class)
    public ResponseEntity<StructCustomException> handlerExistingObjectException(Exception e, WebRequest request){
        var exception = new StructCustomException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InvalidTokenJwtException.class)
    public ResponseEntity<StructCustomException> handlerInvalidTokenJwtException(Exception e, WebRequest request){
        var exception = new StructCustomException(
                new Date(),
                e.getMessage(),
                request.getDescription(false));
        return new ResponseEntity<>(exception, HttpStatus.FORBIDDEN);
    }

}
