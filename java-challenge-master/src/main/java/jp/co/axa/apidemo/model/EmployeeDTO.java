package jp.co.axa.apidemo.model;

import lombok.*;

/**
 * DTO class containing details of employee used for communication between front end application and backend REST APIs
 * @author Sumanjula Roy
 * @version 1.0
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EmployeeDTO {

    //Stores details of employee id for each employee
    private Integer id;

    //Stores details of employee name for each employee
    private String name;

    //Stores details of salary for each employee
    private Long salary;

    //Stores details of organization department to which each employee belongs to
    private String department;

}
