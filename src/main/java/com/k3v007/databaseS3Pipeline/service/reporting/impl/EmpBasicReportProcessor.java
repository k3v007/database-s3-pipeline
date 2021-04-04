package com.k3v007.databaseS3Pipeline.service.reporting.impl;

import com.k3v007.databaseS3Pipeline.dto.EmpBasicReport;
import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import com.k3v007.databaseS3Pipeline.service.platform.ReportExporter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Stream;

/**
 * The type Emp basic report processor.
 *
 * @author Vivek
 */
@Service
public class EmpBasicReportProcessor extends AbstractReportProcessor<EmpBasicReport> {

    private final IEmployeeManager employeeManager;
    private final ReportExporter reportExporter;

    /**
     * Instantiates a new Emp basic report processor.
     *
     * @param employeeManager the employee manager
     * @param reportExporter  the report exporter
     */
    public EmpBasicReportProcessor(IEmployeeManager employeeManager, ReportExporter reportExporter) {
        this.employeeManager = employeeManager;
        this.reportExporter = reportExporter;
    }

    @Override
    public ReportType getReportType() {
        return ReportType.EMP_BASIC_REPORT;
    }

    @Override
    public Class<EmpBasicReport> getReportClass() {
        return EmpBasicReport.class;
    }

    @Override
    public String generateReportFileUrl(Boolean usingStream) {
        String reportFileUrl;
        if (Boolean.TRUE.equals(usingStream)) {
            Stream<Employee> employeeStream = employeeManager.getAllEmployeesStream();
            reportFileUrl = reportExporter.exportToCsv(getReportClass(), employeeStream) ;
        } else {
            // Using normal iterative in-memory approach
            List<Employee> employeesList = employeeManager.findAllEmployees();
            reportFileUrl = reportExporter.exportToCsv(getReportClass(), employeesList);
        }
        return reportFileUrl;
    }
}
