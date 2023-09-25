package jp.co.axa.apidemo.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Class to create custom error message to be sent to client side to represent exceptions in user-friendly manner
 * @author Sumanjula Roy
 * @version 1.0
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDetails {

    //Stores details of time in which exception has occurred
    private LocalDateTime timestamp;

    //Stores details of message to be displayed to end user on encountering exceptions
    private String message;

    //Stores details of REST API path which was called and led to the exception
    private String path;

    //Stores details of the error code for better representation of the type of exception
    private String errorCode;
}
