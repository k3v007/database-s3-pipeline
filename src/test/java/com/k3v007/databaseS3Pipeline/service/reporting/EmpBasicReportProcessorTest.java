package com.k3v007.databaseS3Pipeline.service.reporting;

import com.k3v007.databaseS3Pipeline.DatabaseS3PipelineApplication;
import com.k3v007.databaseS3Pipeline.dto.ReportParam;
import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.service.reporting.impl.EmpBasicReportProcessor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.time.LocalDateTime;

/**
 * The type Emp basic report processor test.
 *
 * @author Vivek
 */
@SpringBootTest(classes = DatabaseS3PipelineApplication.class)
public class EmpBasicReportProcessorTest {

    @Autowired
    private EmpBasicReportProcessor empBasicReportProcessor;

    /**
     * Check all injected beans.
     */
    @Test
    public void checkAllInjectedBeans() {
        Assertions.assertThat(empBasicReportProcessor).isNotNull();
    }

    /**
     * Given report param report should be exported using streams.
     *
     * @throws IOException the io exception
     */
    @Test
    public void givenReportParam_ReportShouldBeExportedUsingStreams() throws IOException {
        ReportParam reportParam = ReportParam.builder()
                .reportType(ReportType.EMP_BASIC_REPORT)
                .requestedBy("test.user@abc.com")
                .requestedAt(LocalDateTime.now())
                .streamEnabled(true)
                .build();
        String reportFileUrl = empBasicReportProcessor.generateReportFileUrl(reportParam);
        Assertions.assertThat(reportFileUrl).isNotNull();
    }
}
