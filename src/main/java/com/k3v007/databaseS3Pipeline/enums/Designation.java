package com.k3v007.databaseS3Pipeline.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * The enum Designation.
 *
 * @author Vivek
 */
@Getter
@AllArgsConstructor
public enum Designation {

    /**
     * Software developer designation.
     */
    SOFTWARE_DEVELOPER(0),
    /**
     * Senior software developer designation.
     */
    SENIOR_SOFTWARE_DEVELOPER(1),
    /**
     * Engineering manager designation.
     */
    ENGINEERING_MANAGER(2),
    /**
     * Senior engineering manager designation.
     */
    SENIOR_ENGINEERING_MANAGER(3);

    private final Integer index;
}
