package jp.co.axa.apidemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception class created to handle exception that might occur during employee CRUD operations where
 * employee is not found.
 *
 * @author Sumanjula Roy
 * @version 1.0
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class EmployeeNotFoundException extends RuntimeException{

    //Name of employee who is not found in database based on fieldName
    private String employeeName;

    //Name of field on the basis of which employee is being fetched from the database
    private String fieldName;

    // Value of the field on the basis of which employee is being fetched from the database
    private Integer fieldValue;

    /**
     * Instantiates a new Employee not found exception.
     *
     * @param employeeName the employee name who is not found in database based on fieldName
     * @param fieldName    the field name on the basis of which employee is being fetched from the database
     * @param fieldValue   the field value on the basis of which employee is being fetched from the database
     */
    public EmployeeNotFoundException(String employeeName, String fieldName, Integer fieldValue){
        super(String.format("Employee %s not found with %s : '%s'", employeeName, fieldName, fieldValue));
        this.employeeName = employeeName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
