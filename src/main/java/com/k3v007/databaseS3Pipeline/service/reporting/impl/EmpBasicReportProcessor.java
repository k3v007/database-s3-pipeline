package com.k3v007.databaseS3Pipeline.service.reporting.impl;

import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Emp basic report processor.
 *
 * @author Vivek
 */
@Service
public class EmpBasicReportProcessor extends AbstractReportProcessor {

    private final IEmployeeManager employeeManager;

    /**
     * Instantiates a new Emp basic report processor.
     *
     * @param employeeManager the employee manager
     */
    public EmpBasicReportProcessor(IEmployeeManager employeeManager) {
        this.employeeManager = employeeManager;
    }

    @Override
    public ReportType getReportType() {
        return ReportType.EMP_BASIC_REPORT;
    }

    @Override
    public List<String> getReportHeader() {
        return Arrays.asList("FIRST NAME", "LAST NAME", "EMPLOYEE CODE", "DESIGNATION");
    }

    @Override
    public List<String> getReportFooter() {
        return Collections.emptyList();
    }

    @Override
    public String generateReportFileUrl() {
        Stream<Employee> employeeStream = employeeManager.findAllEmployeesStream();

        /**
         * Write header
         * Write data
         * Write footer
         * Get File url
         */
        return "";
    }
}
