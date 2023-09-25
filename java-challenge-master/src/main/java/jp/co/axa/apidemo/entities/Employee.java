package jp.co.axa.apidemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Entity class containing details of EMPLOYEE details as in database
 * @author Sumanjula Roy
 * @version 1.0
 */
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EMPLOYEE")
public class Employee {


    //Unique ID of each employee
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    //Name of Employee
    @Column(name="EMPLOYEE_NAME")
    @NotEmpty
    private String name;

    //Salary of employee
    @Column(name="EMPLOYEE_SALARY")
    @NotNull
    private Long salary;

    //Organization department to which an employee belongs
    @Column(name="DEPARTMENT")
    @NotEmpty
    private String department;

}
