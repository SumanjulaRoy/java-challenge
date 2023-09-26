package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Class that implements the {@link EmployeeService} interface and its methods
 * @author Sumanjula Roy
 * @version 1.0
 */
@Service
@Slf4j
@AllArgsConstructor
public class EmployeeServiceImpl implements EmployeeService{


    //EmployeeRepository object to call methods
    private EmployeeRepository employeeRepository;

    //ModelMapper object to convert data from {@link EmployeeDTO} to {@link Employee} and vice versa
    private ModelMapper mapper;

    /**
     * Method to call employee repository to find all employees of the organization
     *
     * @return : the list of all employees in {@link EmployeeDTO} structure
     */
    @Override
    public List<EmployeeDTO> retrieveEmployees() {
        log.info("Calling Employee Repository to fetch details of all employees");
        List<Employee> employees = employeeRepository.findAll();
        log.info("All employee details fetched");
        return employees.stream().map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    /**
     * Method to find details of a particular employee of the organization based on the employee id
     *
     * @param employeeId: the employee id as input whose employee details are to be fetched
     * @return : Details of the employee in {@link EmployeeDTO} structure
     * @throws : {@link EmployeeNotFoundException} in case employee id is not found to be fetched
     */
    @Override
    public EmployeeDTO getEmployee(Integer employeeId) {

        log.info("Calling Employee Repository to fetch details of existing employee");
        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("id", employeeId.toString())
        );
        EmployeeDTO employeeDTO = mapper.map(existingEmployee, EmployeeDTO.class);
        log.info("Employee details fetched successfully for employee id : %s", employeeId);
        return employeeDTO;
    }

    /**
     * Method to save details of a new employee to database
     *
     * @param employee : Details of the employee to be saved
     * @return : Saved details of the employee in {@link EmployeeDTO} structure
     */
    @Override
    public EmployeeDTO saveEmployee(EmployeeDTO employee){
        Employee employeeDetails = mapper.map(employee, Employee.class);
        log.info("Calling Employee Repository to save details of new employee");
        Employee savedEmployeeDetails = employeeRepository.save(employeeDetails);
        log.info("New employee saved successfully");
        return mapper.map(savedEmployeeDetails, EmployeeDTO.class);
    }


    /**
     * Method to find and then delete details of an employee of the organization based on the employee id.
     *
     * @param employeeId : Input based on which employee is to be searched and details deleted
     * @throws : {@link EmployeeNotFoundException} in case employee id is not found to be deleted
     */
    @Override
    public void deleteEmployee(Integer employeeId){

        log.info("Calling Employee Repository to fetch details of employee to be deleted");
        employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("id", employeeId.toString())
        );
        log.info("Calling Employee Repository to delete existing employee");
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Method to find and update details of an existing employee
     *
     * @param employee : Details of the employee whom we want to update
     * @return : Updated details of the employee in {@link EmployeeDTO} structure
     * @throws : {@link EmployeeNotFoundException} in case employee id is not found to be updated
     */
    @Override
    public EmployeeDTO updateEmployee(EmployeeDTO employee) {

        log.info("Calling Employee Repository to fetch details of existing employee");
        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new EmployeeNotFoundException( "id", employee.getId().toString())
        );

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());

        log.info("Calling Employee Repository to update details of existing employee");
        Employee updatedEmployee = employeeRepository.save(existingEmployee);

        log.info("Employee details updated successfully");
        return mapper.map(updatedEmployee, EmployeeDTO.class);
    }

    /**
     * Method to fetch all employees tagged to a particular department
     *
     * @param department : Department name for which user wants to fetch all employees
     * @return : {List<{@link EmployeeDTO}} List of all employees of a particular department
     */
    @Override
    public List<EmployeeDTO> getEmployeesByDepartment(String department) {

        log.info("Calling Employee Repository to fetch details of all employees tagged to department: %s", department);
        List<Employee> employeeListByDept = employeeRepository.findByDepartmentIgnoreCase(department);

       if (employeeListByDept.isEmpty()) {
           throw new EmployeeNotFoundException("department name", department);
       }

        log.info("All employees fetched successfully for department : %s", department);
        return employeeListByDept.stream().map(employee -> mapper.map(employee, EmployeeDTO.class))
                .collect(Collectors.toList());

    }
}