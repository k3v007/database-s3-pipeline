package com.k3v007.databaseS3Pipeline.manager;

import com.k3v007.databaseS3Pipeline.model.GenericReport;

/**
 * The interface Generic report manager.
 *
 * @author Vivek
 */
public interface IGenericReportManager {

    /**
     * Save report generic report.
     *
     * @param genericReport the generic report
     * @return the generic report
     */
    GenericReport saveReport(GenericReport genericReport);
}
