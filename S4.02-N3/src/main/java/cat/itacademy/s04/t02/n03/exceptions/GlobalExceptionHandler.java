package cat.itacademy.s04.t02.n03.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConflictException.class)
    public ResponseEntity<Object> handleConflictException(ConflictException conflictException, WebRequest request){
        HttpStatus status = HttpStatus.CONFLICT;
        String path = request.getDescription(false).replace("uri=", "");

        ErrorResponse errorResponse = new ErrorResponse(
                status,
                conflictException.getMessage(),
                path
        );
        return new ResponseEntity<>(errorResponse, status);

    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException illegalArgExc, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        String errorMsg = illegalArgExc.getMessage();
        String path = request.getDescription(false).replace("uri=", "");

        ErrorResponse errorResponse = new ErrorResponse(status, errorMsg, path);

        return new ResponseEntity<>(errorResponse, status);
    }




}
