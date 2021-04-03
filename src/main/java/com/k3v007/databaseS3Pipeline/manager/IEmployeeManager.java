package com.k3v007.databaseS3Pipeline.manager;

import com.k3v007.databaseS3Pipeline.model.Employee;

import java.util.stream.Stream;

/**
 * The interface Employee manager.
 *
 * @author Vivek
 */
public interface IEmployeeManager {

    /**
     * Find all employees stream stream.
     *
     * @return the stream
     */
    Stream<Employee> findAllEmployeesStream();
}
