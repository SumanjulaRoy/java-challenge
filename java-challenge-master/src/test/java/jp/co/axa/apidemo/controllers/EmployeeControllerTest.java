package jp.co.axa.apidemo.controllers;

import jp.co.axa.apidemo.model.EmployeeDTO;
import jp.co.axa.apidemo.services.EmployeeService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmployeeControllerTest{

    private static final Integer ID = 1;

    @InjectMocks
    private EmployeeController employeeController;

    @Mock
    private EmployeeService employeeService;


    @Test
    public void testGetAllEmployees() {

        // Mock data
        List<EmployeeDTO> mockEmployeesList = Collections.singletonList(
                new EmployeeDTO(ID, "John Doe",50000L, "Sales"));
        when(employeeService.retrieveEmployees()).thenReturn(mockEmployeesList);

        assertNotNull(employeeController.getAllEmployees().getBody());
        assertEquals(1, employeeController.getAllEmployees().getBody().size());

    }

    @Test
    public void testGetEmployee() {
        // Mock data
        EmployeeDTO mockEmployee = new EmployeeDTO(ID, "John Doe",50000L, "Sales");
        when(employeeService.getEmployee(ID)).thenReturn(mockEmployee);

        // Test the controller method
        EmployeeDTO result = employeeController.getEmployee(ID).getBody();

        // Verify the result
        assertNotNull(result);
        assertEquals(mockEmployee.getId(), result.getId());
        assertEquals(mockEmployee.getName(), result.getName());
        assertEquals(mockEmployee.getDepartment(), result.getDepartment());
        assertEquals(mockEmployee.getSalary(), result.getSalary());

    }

}