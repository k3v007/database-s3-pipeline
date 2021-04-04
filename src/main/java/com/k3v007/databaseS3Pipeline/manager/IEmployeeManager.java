package com.k3v007.databaseS3Pipeline.manager;

import com.k3v007.databaseS3Pipeline.model.Employee;

import java.util.List;
import java.util.stream.Stream;

/**
 * The interface Employee manager.
 *
 * @author Vivek
 */
public interface IEmployeeManager {

    /**
     * Find all employees list.
     *
     * @return the list
     */
    List<Employee> findAllEmployees();

    /**
     * Gets all employees stream.
     *
     * @return the stream
     */
    Stream<Employee> getAllEmployeesStream();
}
