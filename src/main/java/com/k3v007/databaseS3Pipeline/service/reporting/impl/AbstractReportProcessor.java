package com.k3v007.databaseS3Pipeline.service.reporting.impl;

import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.service.reporting.IReportProcessor;

import java.util.List;

/**
 * The type Abstract report processor.
 *
 * @author Vivek
 */
public abstract class AbstractReportProcessor implements IReportProcessor {

    public abstract ReportType getReportType();

    /**
     * Gets report header.
     *
     * @return the report header
     */
    public abstract List<String> getReportHeader();

    /**
     * Gets report footer.
     *
     * @return the report footer
     */
    public abstract List<String> getReportFooter();
}
