package cat.itacademy.s04.t02.n02.exceptions;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;



@Data
public class ErrorResponse {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private List<String> details;
    private String path;

    public ErrorResponse(HttpStatus status, String message, List<String> details, String path){
        this.timestamp = LocalDateTime.now();
        this.status = status.value();
        this.error = status.getReasonPhrase();
        this.message = message;
        this.details = details;
        this.path = path;
    }

    public ErrorResponse(HttpStatus status, String message, String path){
        this(status, message, null, path);
    }


}