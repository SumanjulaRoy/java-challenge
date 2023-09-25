package jp.co.axa.apidemo.services;

import jp.co.axa.apidemo.entities.Employee;
import jp.co.axa.apidemo.exception.EmployeeNotFoundException;
import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.repositories.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;


@SpringBootTest
public class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;


    @Spy
    private ModelMapper mapper;

    @InjectMocks
    private EmployeeServiceImpl employeeService;

    private static final Integer ID = 1;
    private static final String DEPARTMENT = "DEPARTMENT";
    private static final String NAME = "FIRSTNAME LASTNAME";
    private static final Long SALARY = 50000L;

    @Test
    public void testRetrieveEmployees() {

        Employee employee = givenEmployee();
        List<Employee> employeeList = Collections.singletonList(employee);
        when(employeeRepository.findAll()).thenReturn(employeeList);
        assertEquals(employeeList, employeeService.retrieveEmployees().stream()
                .map(employeeDTO -> mapper.map(employeeDTO, Employee.class))
                .collect(Collectors.toList()));

    }

    @Test
    public void testGetEmployeeById() {

        Employee employee = givenEmployee();
        when(employeeRepository.findById(ID)).thenReturn(Optional.of(employee));
        assertEquals(employee, mapper.map(employeeService.getEmployee(ID), Employee.class) );
    }

    @Test
    public void testGetEmployeeByInvalidId() {

        when(employeeRepository.findById(2)).thenReturn(Optional.empty());
        assertThrows(EmployeeNotFoundException.class, () -> employeeService.getEmployee(2),
                "errorMessage");
    }

    @Test
    public void testSaveEmployee() {

        Employee employee = givenEmployee();
        employee.setId(0);
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, mapper.map(employeeService.saveEmployee(mapper.map(employee, EmployeeDTO.class)),
                Employee.class));
    }

    @Test
    public void testUpdateEmployee() {

        Employee employee = givenEmployee();
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        when(employeeRepository.save(employee)).thenReturn(employee);
        assertEquals(employee, mapper.map(employeeService.updateEmployee(mapper.map(employee, EmployeeDTO.class)),
                Employee.class));
    }

    @Test
    public void testDeleteEmployee() {

        Employee employee = givenEmployee();
        when(employeeRepository.findById(employee.getId())).thenReturn(Optional.of(employee));
        doNothing().when(employeeRepository).deleteById(ID);
    }

    private Employee givenEmployee() {

        return Employee.builder().id(ID).name(NAME).department(DEPARTMENT).salary(SALARY)
                .build();

    }

}