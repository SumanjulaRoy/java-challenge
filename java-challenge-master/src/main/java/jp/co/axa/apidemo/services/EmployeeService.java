package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.model.EmployeeDTO;

import java.util.List;

/**
 * EmployeeService interface to declare all employee related functionalities which will be called from controller methods
 * @author Sumanjula Roy
 * @version 1.0
 */
public interface EmployeeService {

    /**
     * Method to retrieve details of all employees of the organization
     *
     * @return : the list of all employees in {@link EmployeeDTO} structure
     */
    public List<EmployeeDTO> retrieveEmployees();

    /**
     * Method to retrieve details of a particular employee of the organization based on the employee id
     *
     * @param employeeId: the employee id as input whose employee details are to be fetched
     * @return : Details of the employee in {@link EmployeeDTO} structure
     */
    public EmployeeDTO getEmployee(Integer employeeId);

    /**
     * Method to save details of a new employee to database
     *
     * @param employee : Details of the employee to be saved
     * @return : Saved details of the employee in {@link EmployeeDTO} structure
     */
    public EmployeeDTO saveEmployee(EmployeeDTO employee);

    /**
     * Method to delete details of an employee of the organization based on the employee id.
     *
     * @param employeeId : Input based on which employee is to be searched and details deleted
     */
    public void deleteEmployee(Integer employeeId);

    /**
     * Method to update details of an existing employee
     *
     * @param employee : Details of the employee whom we want to update
     * @return : Updated details of the employee in {@link EmployeeDTO} structure
     */
    public EmployeeDTO updateEmployee(EmployeeDTO employee);

    /**
     * Method to fetch all employees tagged to a particular department
     * @param department : Department name for which user wants to fetch all employees
     * @return : {List<{@link EmployeeDTO}} List of all employees of a particular department
     */
    public List<EmployeeDTO> getEmployeesByDepartment(String department);
}