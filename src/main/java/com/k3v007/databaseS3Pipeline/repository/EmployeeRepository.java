package com.k3v007.databaseS3Pipeline.repository;

import com.k3v007.databaseS3Pipeline.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.stream.Stream;

/**
 * The interface Employee repository.
 *
 * @author Vivek
 */
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {

    /**
     * Find by employee code employee.
     *
     * @param employeeCode the employee code
     * @return the employee
     */
    Employee findByEmployeeCode(Integer employeeCode);

    /**
     * Find all using stream stream.
     *
     * @return the stream
     */
    Stream<Employee> findAllUsingStream();
}
