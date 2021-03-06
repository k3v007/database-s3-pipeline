package com.k3v007.databaseS3Pipeline.service.reporting.impl;

import com.k3v007.databaseS3Pipeline.dto.EmpBasicReport;
import com.k3v007.databaseS3Pipeline.dto.ReportParam;
import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import com.k3v007.databaseS3Pipeline.service.platform.ReportExporter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
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
    @Autowired
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
    public String generateReportFileUrl(ReportParam reportParam) throws IOException {
        String reportFileUrl;
        if (Boolean.TRUE.equals(reportParam.getStreamEnabled())) {
            Stream<Employee> employeeStream = employeeManager.getAllEmployeesStream();
            reportFileUrl = reportExporter.exportToCsv(getReportClass(), employeeStream, reportParam.getReportName());
        } else {
            // Using normal iterative in-memory approach
            List<Employee> employeesList = employeeManager.findAllEmployees();
            reportFileUrl = reportExporter.exportToCsv(getReportClass(), employeesList, reportParam.getReportName());
        }
        return reportFileUrl;
    }
}
