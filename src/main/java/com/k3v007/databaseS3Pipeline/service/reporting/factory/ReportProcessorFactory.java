package com.k3v007.databaseS3Pipeline.service.reporting.factory;

import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.exception.EmsBaseException;
import com.k3v007.databaseS3Pipeline.service.reporting.IReportProcessor;
import com.k3v007.databaseS3Pipeline.service.reporting.impl.EmpBasicReportProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * The type Report processor factory.
 *
 * @author Vivek
 */
@Service
public class ReportProcessorFactory {

    private final EmpBasicReportProcessor empBasicReportProcessor;
    private final Map<ReportType, IReportProcessor> REPORT_PROCESSOR_MAP = new HashMap<>();

    /**
     * Instantiates a new Report processor factory.
     *
     * @param empBasicReportProcessor the emp basic report processor
     */
    @Autowired
    public ReportProcessorFactory(EmpBasicReportProcessor empBasicReportProcessor) {
        this.empBasicReportProcessor = empBasicReportProcessor;
    }

    /**
     * Build factory.
     */
    @PostConstruct
    public void buildFactory() {
        REPORT_PROCESSOR_MAP.put(ReportType.EMP_BASIC_REPORT, empBasicReportProcessor);
    }

    /**
     * Gets reporting processor.
     *
     * @param reportType the report type
     * @return the reporting processor
     */
    public IReportProcessor getReportingProcessor(ReportType reportType) {
        return Optional.ofNullable(REPORT_PROCESSOR_MAP.get(reportType))
                .orElseThrow(() -> new EmsBaseException("Reporting type not defined"));
    }
}
