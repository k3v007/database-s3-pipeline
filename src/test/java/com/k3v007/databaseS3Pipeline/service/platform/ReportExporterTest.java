package com.k3v007.databaseS3Pipeline.service.platform;

import com.k3v007.databaseS3Pipeline.DatabaseS3PipelineApplication;
import com.k3v007.databaseS3Pipeline.dto.EmpBasicReport;
import com.k3v007.databaseS3Pipeline.manager.IEmployeeManager;
import com.k3v007.databaseS3Pipeline.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

/**
 * The type Report exporter test.
 *
 * @author Vivek
 */
@Slf4j
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
     * Test report export to csv using jpa and s3 streams.
     *
     * @throws IOException the io exception
     */
    @Test
    @Transactional(readOnly = true)
    public void testReportExportToCsvUsingJpaAndS3Streams() throws IOException {
        Stream<Employee> employeeStream = employeeManager.getAllEmployeesStream();
        String reportFileUrl = reportExporter.exportToCsv(EmpBasicReport.class, employeeStream, "TestFile");
        Assertions.assertThat(reportFileUrl).isNotNull();
    }

    /**
     * Test report export to csv without stream.
     *
     * @throws IOException the io exception
     */
    @Test
    @Transactional(readOnly = true)
    public void testReportExportToCsvWithoutStream() throws IOException {
        log.info("Data fetch started");
        List<Employee> employeesList = employeeManager.findAllEmployees();
        log.info("Data fetch completed");
        String reportFileUrl = reportExporter.exportToCsv(EmpBasicReport.class, employeesList, "TestFile2");
        Assertions.assertThat(reportFileUrl).isNotNull();
    }
}
