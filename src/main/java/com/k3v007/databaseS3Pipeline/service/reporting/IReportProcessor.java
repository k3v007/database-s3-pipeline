package com.k3v007.databaseS3Pipeline.service.reporting;

import com.k3v007.databaseS3Pipeline.enums.ReportType;

/**
 * The interface Report processor.
 *
 * @author Vivek
 */
public interface IReportProcessor {

    /**
     * Gets report type.
     *
     * @return the report type
     */
    ReportType getReportType();

    /**
     * Generate report file url string.
     *
     * @return the string
     */
    String generateReportFileUrl();
}
