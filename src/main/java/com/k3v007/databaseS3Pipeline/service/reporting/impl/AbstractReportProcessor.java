package com.k3v007.databaseS3Pipeline.service.reporting.impl;

import com.k3v007.databaseS3Pipeline.enums.ReportType;
import com.k3v007.databaseS3Pipeline.service.reporting.IReportProcessor;

/**
 * The type Abstract report processor.
 *
 * @param <T> the type parameter
 * @author Vivek
 */
public abstract class AbstractReportProcessor<T> implements IReportProcessor {

    public abstract ReportType getReportType();

    /**
     * Gets report class.
     *
     * @return the report class
     */
    protected abstract Class<T> getReportClass();

    /**
     * Generate report file url string.
     *
     * @param usingStream the using stream
     * @return the string
     */
    public abstract String generateReportFileUrl(Boolean usingStream);
}
