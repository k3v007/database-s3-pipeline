package com.k3v007.databaseS3Pipeline.service;

import com.k3v007.databaseS3Pipeline.dto.ReportParam;

/**
 * The interface Report service.
 *
 * @author Vivek
 */
public interface IReportService {

    /**
     * Generate report.
     *
     * @param reportParam the report param
     */
    void generateReport(ReportParam reportParam);
}
