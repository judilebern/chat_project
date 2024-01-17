package project;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;

@Getter
@AllArgsConstructor
public class ApplicationException extends RuntimeException {

    private final String errorCode;
    private final String message;
    private final HttpStatus httpStatus;
}

