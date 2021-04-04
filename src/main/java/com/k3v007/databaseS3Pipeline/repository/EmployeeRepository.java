package com.k3v007.databaseS3Pipeline.repository;

import com.k3v007.databaseS3Pipeline.model.Employee;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
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
     * Find all by id less than list.
     *
     * @param id the id
     * @return the list
     */
    List<Employee> findAllByIdLessThan(Integer id);

    /**
     * Find all using stream stream.
     *
     * @param id       the id
     * @param pageable the pageable
     * @return the stream
     */
    @Query(value = "SELECT * FROM employees AS e WHERE e.id < :id", nativeQuery = true)
    Stream<Employee> findAllUsingStreamLessThan(@Param("id") Integer id, @Param("pageable") Pageable pageable);
}
