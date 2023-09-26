package jp.co.axa.apidemo.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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
    @Operation(summary = "Get details of all employees in the organization")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All employee details fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "500", description = "Something went wrong while fetching all employees",
                    content = @Content) })
    @GetMapping
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        log.info("Controller method to fetch all employees in the organization");
        return ResponseEntity.ok().body(employeeService.retrieveEmployees());
    }

    /**
     * REST API to retrieve details of a particular employee via GET request.
     *
     * @param employeeId: Employee ID for which consumer wants the employee details to be fetched from database
     * @return : ResponseEntity containing either details of employee or proper error message if any
     */
    @Operation(summary = "Get details of a particular employee based on employee id input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not found with given Employee Id",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Error occurred while fetching employee details by employee id",
                    content = @Content)})

    @GetMapping("{employeeId}")
    public ResponseEntity<EmployeeDTO> getEmployee(@Parameter(description = "Id of the employee whose details is to be fetched")
                                                       @PathVariable(name="employeeId") Integer employeeId) {

        log.info("Controller method to fetch employee details for Employee ID: %s", employeeId);
        return ResponseEntity.ok().body(employeeService.getEmployee(employeeId));
    }


    /**
     * REST API to save details of a new user in database using POST request
     * @param employee : Contains details of new employee to be saved.
     * @return : ResponseEntity containing details of the saved employee along with HTTP status code.
     */
    @Operation(summary = "Add a new employee and save details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "New employee details saved successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "500",
                    description = "Error occurred while creating and saving new employee details",
                    content = @Content)})
    @PostMapping
    public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody EmployeeDTO employee){

        log.info("Controller method to create and save new employee");
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
    @Operation(summary = "Delete details of a particular employee based on employee id input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details deleted successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not found with given Employee Id",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Error occurred while deleting employee details by employee id",
                    content = @Content)})

    @DeleteMapping("{employeeId}")
    public ResponseEntity<String> deleteEmployee(@Parameter(description = "Id of the employee whose details is to be deleted")
                                                     @PathVariable Integer employeeId){

        log.info("Controller method to delete employee with employee id : %s", employeeId);
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
    @Operation(summary = "Update details of an existing employee based on employee id input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Employee details updated successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "404", description = "Employee not found with given Employee Id",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Error occurred while updating employee details by employee id",
                    content = @Content)})
    @PutMapping("{employeeId}")
    public ResponseEntity<EmployeeDTO> updateEmployee(@RequestBody EmployeeDTO employeeDTO,
                                                      @Parameter(description = "Id of the employee whose details is to be updated")
                                                      @PathVariable Integer employeeId){

        log.info("Controller method to update employee with employee id : %s", employeeId);
        employeeDTO.setId(employeeId);
        EmployeeDTO updatedEmployee = employeeService.updateEmployee(employeeDTO);
        return ResponseEntity.ok().body(updatedEmployee);
    }

    /**
     * REST API to retrieve details of all employees tagged to a particular department via GET request.
     *
     * @param department: Department name for which consumer wants all employee details to be fetched from database
     * @return : ResponseEntity containing either details of all employees tagged to the department or proper error message if any
     */
    @Operation(summary = "Get details of all employees tagged to a particular department based on department name input")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",
                    description = "All employees tagged to input department fetched successfully",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = EmployeeDTO.class)) }),
            @ApiResponse(responseCode = "404",
                    description = "No employee found for given department or department name invalid",
                    content = @Content),
            @ApiResponse(responseCode = "500",
                    description = "Error occurred while fetching employee details by department name",
                    content = @Content)})

    @GetMapping("/departmentName/{department}")
    public ResponseEntity<List<EmployeeDTO>> getEmployeesByDepartment(
            @Parameter(description = "Department for which all employees are to be fetched")
            @PathVariable(name="department") String department) {

        log.info("Controller method to fetch all employees belonging to department : %s", department);
        return ResponseEntity.ok().body(employeeService.getEmployeesByDepartment(department));
    }

}
