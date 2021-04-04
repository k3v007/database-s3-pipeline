package com.k3v007.databaseS3Pipeline.service.platform;

import alex.mojaki.s3upload.StreamTransferManager;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.k3v007.databaseS3Pipeline.exception.EmsBaseException;
import com.k3v007.databaseS3Pipeline.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
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
    private String awsS3Bucket;

    private final AmazonS3 s3Client;

    /**
     * Instantiates a new Report exporter.
     *
     * @param s3Client the amazon s 3
     */
    @Autowired
    public ReportExporter(AmazonS3 s3Client) {
        this.s3Client = s3Client;
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
    public <T, U> String exportToCsv(Class<T> reportClass, Stream<U> dataStream, String filePath) throws IOException {
        StreamTransferManager streamTransferManager = new StreamTransferManager(awsS3Bucket, filePath, s3Client);
        OutputStream outputStream = streamTransferManager.getMultiPartOutputStreams().get(0);
        CsvMapper csvMapper = new CsvMapper();
        CsvGenerator csvGenerator = csvMapper.getFactory().createGenerator(outputStream);
        dataStream.forEach(data -> {
            try {
                csvMapper.writerFor(dataStream.getClass())
                        .with(CsvUtil.buildCsvSchema(reportClass))
                        .writeValue(outputStream, data);
            } catch (IOException e) {
                throw new EmsBaseException("Something went wrong");
            }
        });
        outputStream.close();
        streamTransferManager.complete();
        s3Client.setObjectAcl(awsS3Bucket, filePath, CannedAccessControlList.PublicRead);
        URL url = s3Client.getUrl(awsS3Bucket, filePath);
        return url.toString();
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
    public <T, U> String exportToCsv(Class<T> reportClass, List<U> dataStream, String filePath) {
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
