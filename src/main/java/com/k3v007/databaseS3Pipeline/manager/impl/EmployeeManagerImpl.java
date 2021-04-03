package com.k3v007.databaseS3Pipeline.manager.impl;

import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import com.k3v007.databaseS3Pipeline.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

/**
 * The type Employee manager.
 *
 * @author Vivek
 */
@Component
public class EmployeeManagerImpl implements IEmployeeManager {

    private final EmployeeRepository employeeRepository;

    /**
     * Instantiates a new Employee manager.
     *
     * @param employeeRepository the employee repository
     */
    @Autowired
    public EmployeeManagerImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Stream<Employee> findAllEmployeesStream() {
        return null;
    }
}
