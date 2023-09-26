package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Employee Repository interface to interact with database for performing CRUD operations
 * @author Sumanjula Roy
 * @version 1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {

    /**
     * Find employees belonging to a particular department
     * @param department : Input department name
     * @return : List of all employees tagged to a particular department
     */
    List<Employee> findByDepartmentIgnoreCase(String department);
}
