package com.k3v007.databaseS3Pipeline.manager;

import com.k3v007.databaseS3Pipeline.DatabaseS3PipelineApplication;
import com.k3v007.databaseS3Pipeline.constant.EmployeeConstant;
import com.k3v007.databaseS3Pipeline.model.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * The type Employee manager test.
 *
 * @author Vivek
 */
@SpringBootTest(classes = DatabaseS3PipelineApplication.class)
public class EmployeeManagerTest {

    @Autowired
    private IEmployeeManager employeeManager;

    /**
     * Check all injected beans.
     */
    @Test
    public void checkAllInjectedBeans() {
        assertThat(employeeManager).isNotNull();
    }

    /**
     * Count of all employees should be less than the id constant.
     */
    @Test
    public void countOfAllEmployeesShouldBeLessThanTheIdConstant() {
        List<Employee> employeeList = employeeManager.findAllEmployees();
        Integer employeesCount = employeeList.size();
        assertThat(employeesCount).isLessThan(EmployeeConstant.MAX_ID_THRESHOLD);
    }

    /**
     * Count of all employees using stream should be less than the id constant.
     */
    @Test
    @Transactional(readOnly = true)
    public void countOfAllEmployeesUsingStreamShouldBeLessThanTheIdConstant() {
        Stream<Employee> employeeStream = employeeManager.getAllEmployeesStream();
        Integer employeesCount = (int) employeeStream.count();
        assertThat(employeesCount).isLessThan(EmployeeConstant.MAX_ID_THRESHOLD);
    }
}
