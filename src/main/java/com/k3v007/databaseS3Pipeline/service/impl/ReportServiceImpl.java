package com.k3v007.databaseS3Pipeline.service.impl;

import com.k3v007.databaseS3Pipeline.dto.ReportParam;
import com.k3v007.databaseS3Pipeline.manager.IGenericReportManager;
import com.k3v007.databaseS3Pipeline.model.GenericReport;
import com.k3v007.databaseS3Pipeline.service.IReportService;
import com.k3v007.databaseS3Pipeline.service.reporting.IReportProcessor;
import com.k3v007.databaseS3Pipeline.service.reporting.factory.ReportProcessorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Report service.
 *
 * @author Vivek
 */
@Service
public class ReportServiceImpl implements IReportService {

    private final ReportProcessorFactory reportProcessorFactory;
    private final IGenericReportManager genericReportManager;

    /**
     * Instantiates a new Report service.
     *
     * @param reportProcessorFactory the report processor factory
     * @param genericReportManager   the generic report manager
     */
    @Autowired
    public ReportServiceImpl(ReportProcessorFactory reportProcessorFactory, IGenericReportManager genericReportManager) {
        this.reportProcessorFactory = reportProcessorFactory;
        this.genericReportManager = genericReportManager;
    }

    @Override
    public void generateReport(ReportParam reportParam) {
        IReportProcessor reportProcessor = reportProcessorFactory.getReportingProcessor(reportParam.getReportType());
        String s3FileUrl = reportProcessor.generateReportFileUrl(true);
        GenericReport genericReport = GenericReport.builder()
                .reportName(reportParam.getReportName())
                .reportType(reportParam.getReportType())
                .fileUrl(s3FileUrl)
                .requestedBy(reportParam.getRequestedBy())
                .requestedAt(reportParam.getRequestedAt())
                .build();
        genericReportManager.saveReport(genericReport);
    }
}
