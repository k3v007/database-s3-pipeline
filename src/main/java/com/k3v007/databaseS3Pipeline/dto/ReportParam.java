package com.k3v007.databaseS3Pipeline.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.k3v007.databaseS3Pipeline.enums.ReportType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.apache.commons.lang3.RandomStringUtils;

import java.time.LocalDateTime;

/**
 * The type Report param.
 *
 * @author Vivek
 */
@Data
@Builder
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ReportParam {

    @JsonProperty("report_type")
    private ReportType reportType;

    @JsonProperty("requested_by")
    private String requestedBy;

    @JsonProperty("requested_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime requestedAt;

    /**
     * Gets report name.
     *
     * @return the report name
     */
    @JsonIgnore
    public String getReportName() {
        return String.format("%s-%s-%s", this.reportType.toString(), this.requestedAt.toString(), RandomStringUtils.randomAlphanumeric(10));
    }
}
