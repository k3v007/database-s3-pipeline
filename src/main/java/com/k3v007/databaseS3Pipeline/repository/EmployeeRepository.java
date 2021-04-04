package com.k3v007.databaseS3Pipeline.repository;

import com.k3v007.databaseS3Pipeline.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import javax.persistence.QueryHint;
import java.util.stream.Stream;

import static org.hibernate.jpa.QueryHints.HINT_FETCH_SIZE;

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
    @QueryHints(value = @QueryHint(name = HINT_FETCH_SIZE, value = "" + Integer.MIN_VALUE))
    @Query("select e from Employee e")
    Stream<Employee> findAllUsingStream();
}
