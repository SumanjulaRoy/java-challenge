package jp.co.axa.apidemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

/**
 * Class that handles the different custom as well as generic exceptions for all REST APIs in the application
 * @author Sumanjula Roy
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Method to handle employee not found custom exception.
     *
     * @param exception  : Details of the EmployeeNotFoundException
     * @param webRequest : Details of the web request
     * @return : ResponseEntity containing details of the exception in defined format for user readability
     */
    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleEmployeeNotFoundException(EmployeeNotFoundException exception,
                                                                        WebRequest webRequest){

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "EMPLOYEE_NOT_FOUND"
        );

        return new ResponseEntity<>(exceptionDetails, HttpStatus.NOT_FOUND);
    }

    /**
     * Method to handle global exceptions throughout the application.
     *
     * @param exception  : Details of the Exception
     * @param webRequest : Details of the web request
     * @return : ResponseEntity containing details of the exception in defined format for user readability
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGlobalException(Exception exception,
                                                                          WebRequest webRequest){

        ExceptionDetails exceptionDetails = new ExceptionDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "INTERNAL SERVER ERROR"
        );

        return new ResponseEntity<>(exceptionDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}