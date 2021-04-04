package com.k3v007.databaseS3Pipeline.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.k3v007.databaseS3Pipeline.enums.Designation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

/**
 * The type Emp basic report.
 *
 * @author Vivek
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EmpBasicReport {

    @JsonProperty("FIRST NAME")
    private String firstName;

    @JsonProperty("LAST NAME")
    private String lastName;

    @JsonProperty("EMPLOYEE CODE")
    private Integer employeeCode;

    @JsonProperty("DESIGNATION")
    private Designation designation;

    @JsonProperty("JOINING DATE")
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate joiningDate;
}
