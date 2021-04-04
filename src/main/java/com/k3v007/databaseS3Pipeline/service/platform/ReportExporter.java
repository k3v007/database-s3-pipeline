package com.k3v007.databaseS3Pipeline.service.platform;

import com.amazonaws.services.s3.AmazonS3;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.k3v007.databaseS3Pipeline.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

/**
 * The type Report exporter.
 *
 * @author Vivek
 */
@Component
public class ReportExporter {

    @Value("${aws.s3.bucket}")
    private String s3Bucket;

    private final AmazonS3 amazonS3;

    /**
     * Instantiates a new Report exporter.
     *
     * @param amazonS3 the amazon s 3
     */
    @Autowired
    public ReportExporter(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    /**
     * Export to csv string.
     *
     * @param <T>         the type parameter
     * @param <U>         the type parameter
     * @param reportClass the report class
     * @param dataStream  the data stream
     * @return the string
     */
    public <T, U> String exportToCsv(Class<T> reportClass, Stream<U> dataStream) {
        CsvMapper csvMapper = new CsvMapper();
        dataStream.forEach(s -> {
            try {
                csvMapper.writerFor(dataStream.getClass())
                        .with(CsvUtil.buildCsvSchema(reportClass))
                        .writeValueAsString(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return "";
    }

    /**
     * Export to csv string.
     *
     * @param <T>         the type parameter
     * @param <U>         the type parameter
     * @param reportClass the report class
     * @param dataStream  the data stream
     * @return the string
     */
    public <T, U> String exportToCsv(Class<T> reportClass, List<U> dataStream) {
        CsvSchema csvSchema = CsvUtil.buildCsvSchema(reportClass);
        CsvMapper csvMapper = new CsvMapper();
        dataStream.forEach(s -> {
            try {
                csvMapper.writerFor(dataStream.getClass())
                        .with(CsvUtil.buildCsvSchema(reportClass))
                        .writeValueAsString(s);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
        });
        return "";
    }
}
