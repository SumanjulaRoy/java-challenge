package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.services.EmployeeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

/**
 * Controller class containing REST APIs which call service class methods to perform operations
 * on Employees such as retrieve, create, update and delete employee details.
 *
 * @author Sumanjula Roy
 * @version 1.0
 */
@Slf4j
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/employees")
public class EmployeeController {

    private EmployeeService employeeService;

    /**
     * Method to set employee service.
     *
     * @param employeeService : Employee service to perform different operations on employees
     */
    public void setEmployeeService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    /**
     * REST API to retrieve details of all employees via GET request.
     *
     * @return : List of details of the all employees stored in database
     */
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {
        return ResponseEntity.ok().body(employeeService.retrieveEmployees());
    }

    /**
     * REST API to retrieve details of a particular employee via GET request.
     *
     * @param employeeId: Employee ID for which consumer wants the employee details to be fetched from database
     * @return : ResponseEntity containing either details of employee or proper error message if any
     */
    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@PathVariable(name="employeeId") Integer employeeId) {
        return ResponseEntity.ok().body(employeeService.getEmployee(employeeId));
    }


    /**
     * REST API to save details of a new user in database using POST request
     * @param employee : Contains details of new employee to be saved.
     * @return : ResponseEntity containing details of the saved employee along with HTTP status code.
     */
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(EmployeeDTO employee){

        EmployeeDTO savedEmployee = employeeService.saveEmployee(employee);
        URI location= ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(savedEmployee.getId()).toUri();
        return ResponseEntity.created(location).build();
    }

    /**
     * REST API to delete an existing employee from database using DELETE request
     *
     * @param employeeId : the employee id to be deleted
     * @return : ResponseEntity containing appropriate deletion or error message
     */
    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@PathVariable Integer employeeId){
        employeeService.deleteEmployee(employeeId);
        return ResponseEntity.ok().body("Employee successfully deleted!");
    }

    /**
     * REST API to update details of an existing employee in database using PUT request
     *
     * @param employeeDTO:  the employee details of the employee to be updated
     * @param employeeId:  the employee id of the employee whose details are to be updated
     * @return: ResponseEntity containing updated employee details or error message if any
     */
    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                               @PathVariable Integer employeeId){

        employeeDTO.setId(employeeId);
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.ok().body(updatedEmployee);
    }

}
