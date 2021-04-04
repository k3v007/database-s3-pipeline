package com.k3v007.databaseS3Pipeline.service.reporting;

import com.k3v007.databaseS3Pipeline.dto.ReportParam;
import com.k3v007.databaseS3Pipeline.enums.ReportType;

import java.io.IOException;

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
     * @param reportParam the report param
     * @return the string
     * @throws IOException the io exception
     */
    String generateReportFileUrl(ReportParam reportParam) throws IOException;
}
