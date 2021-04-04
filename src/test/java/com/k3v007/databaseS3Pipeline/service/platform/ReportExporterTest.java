package com.k3v007.databaseS3Pipeline.service.platform;

import com.k3v007.databaseS3Pipeline.DatabaseS3PipelineApplication;
import com.k3v007.databaseS3Pipeline.dto.EmpBasicReport;
import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.stream.Stream;

/**
 * The type Report exporter test.
 *
 * @author Vivek
 */
@SpringBootTest(classes = DatabaseS3PipelineApplication.class)
public class ReportExporterTest {

    @Autowired
    private IEmployeeManager employeeManager;

    @Autowired
    private ReportExporter reportExporter;

    /**
     * Check all injected beans.
     */
    @Test
    public void checkAllInjectedBeans() {
        Assertions.assertThat(employeeManager).isNotNull();
        Assertions.assertThat(reportExporter).isNotNull();
    }

    /**
     * Sample test.
     *
     * @throws IOException the io exception
     */
    @Test
    @Transactional(readOnly = true)
    public void sampleTest() throws IOException {
        Stream<Employee> employeeStream = employeeManager.getAllEmployeesStream();
        String reportFileUrl = reportExporter.exportToCsv(EmpBasicReport.class, employeeStream, "TestFile");
        Assertions.assertThat(reportFileUrl).isNotNull();
    }
}
