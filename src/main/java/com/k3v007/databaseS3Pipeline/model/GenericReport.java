package com.k3v007.databaseS3Pipeline.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.k3v007.databaseS3Pipeline.enums.ReportType;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;

/**
 * The type Generic report.
 *
 * @author Vivek
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "generic_reports")
@EqualsAndHashCode(callSuper = true)
public class GenericReport extends AbstractEntity<Integer> {

    @Column(name = "report_name")
    private String reportName;

    @Column(name = "report_type")
    private ReportType reportType;

    @Column(name = "file_url")
    private String fileUrl;

    @Column(name = "requested_by")
    private String requestedBy;

    @Column(name = "requested_at")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime requestedAt;
}
