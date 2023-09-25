package jp.co.axa.apidemo.repositories;

import jp.co.axa.apidemo.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Employee Repository interface to interact with database for performing CRUD operations
 * @author Sumanjula Roy
 * @version 1.0
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
}
