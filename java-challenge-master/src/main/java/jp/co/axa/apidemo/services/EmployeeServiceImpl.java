package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
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
    public List<EmployeeDTO> retrieveEmployees() {
        List<Employee> employees = employeeRepository.findAll();
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
    public EmployeeDTO getEmployee(Integer employeeId) {

        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee", "id", employeeId)
        );
        EmployeeDTO employeeDTO = mapper.map(existingEmployee, EmployeeDTO.class);
        return employeeDTO;
    }

    /**
     * Method to save details of a new employee to database
     *
     * @param employee : Details of the employee to be saved
     * @return : Saved details of the employee in {@link EmployeeDTO} structure
     */
    public EmployeeDTO saveEmployee(EmployeeDTO employee){
        Employee employeeDetails = mapper.map(employee, Employee.class);
        Employee savedEmployeeDetails = employeeRepository.save(employeeDetails);
        return mapper.map(savedEmployeeDetails, EmployeeDTO.class);
    }


    /**
     * Method to find and then delete details of an employee of the organization based on the employee id.
     *
     * @param employeeId : Input based on which employee is to be searched and details deleted
     * @throws : {@link EmployeeNotFoundException} in case employee id is not found to be deleted
     */
    public void deleteEmployee(Integer employeeId){

        Employee existingEmployee = employeeRepository.findById(employeeId).orElseThrow(
                () -> new EmployeeNotFoundException("Employee", "id", employeeId)
        );
        employeeRepository.deleteById(employeeId);
    }

    /**
     * Method to find and update details of an existing employee
     *
     * @param employee : Details of the employee whom we want to update
     * @return : Updated details of the employee in {@link EmployeeDTO} structure
     * @throws : {@link EmployeeNotFoundException} in case employee id is not found to be updated
     */
    public EmployeeDTO updateEmployee(EmployeeDTO employee) {

        Employee existingEmployee = employeeRepository.findById(employee.getId()).orElseThrow(
                () -> new EmployeeNotFoundException("Employee", "id", employee.getId())
        );

        existingEmployee.setName(employee.getName());
        existingEmployee.setSalary(employee.getSalary());
        existingEmployee.setDepartment(employee.getDepartment());
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        return mapper.map(updatedEmployee, EmployeeDTO.class);
    }
}