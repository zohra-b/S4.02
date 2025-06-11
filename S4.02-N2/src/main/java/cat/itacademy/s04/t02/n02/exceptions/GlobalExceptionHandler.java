package cat.itacademy.s04.t02.n02.exceptions;


import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<Object> noSuchElementException(
            NoSuchElementException noSuchElementException,
            WebRequest request){

        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorMsg = "Requested fruit could not be found";
        String path = request.getDescription(false).replace("uri=", "");

        ErrorResponse errorResponse = new ErrorResponse(
                status,
                errorMsg,
                path);

        return new ResponseEntity<>(errorResponse, status);
    }

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(
            ConflictException conflictException,
            WebRequest request){
            HttpStatus status = HttpStatus.CONFLICT;
            String path = request.getDescription(false).replace("uri=", "");

            ErrorResponse errorResponse = new ErrorResponse(
                    status,
                    conflictException.getMessage(),
                    path
            );

            return new ResponseEntity<>(errorResponse, status);

    }

    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                                                                   MethodArgumentNotValidException argumentNotValidException,
                                                                   HttpHeaders headers,
                                                                   HttpStatus status,
                                                                   WebRequest request){


        List<String> errors = argumentNotValidException.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> error.getField() + " : " + error.getDefaultMessage())
                .toList();

        String path = request.getDescription(false).replace("uri=", "");
        String errorMessage = "Validation failed for one or more fields";
        ErrorResponse errorResponse = new ErrorResponse(
                HttpStatus.BAD_REQUEST,
                errorMessage,
                errors,
                path );

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleOtherUncaughtExceptions(Exception exception, WebRequest request){
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;
        String errorMsg = "An unexpected error occurred";
        String path = request.getDescription(false).replace("uri=", "");

        ErrorResponse errorResponse = new ErrorResponse(
                status,
                errorMsg,
                path );

        return new ResponseEntity<>(errorResponse, status);
    }


}