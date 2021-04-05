package com.k3v007.databaseS3Pipeline.service.platform;

import alex.mojaki.s3upload.StreamTransferManager;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.fasterxml.jackson.dataformat.csv.CsvGenerator;
import com.fasterxml.jackson.dataformat.csv.CsvMapper;
import com.fasterxml.jackson.dataformat.csv.CsvSchema;
import com.k3v007.databaseS3Pipeline.exception.EmsBaseException;
import com.k3v007.databaseS3Pipeline.model.Employee;
import com.k3v007.databaseS3Pipeline.service.mapper.EmployeeMapper;
import com.k3v007.databaseS3Pipeline.util.CsvUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.io.File;
import java.io.FileOutputStream;
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

    private final AmazonS3 awsS3Client;
    private final EmployeeMapper employeeMapper;
    private final EntityManager entityManager;

    /**
     * Instantiates a new Report exporter.
     *
     * @param awsS3Client    the amazon s3
     * @param employeeMapper the employee mapper
     * @param entityManager  the entity manager
     */
    @Autowired
    public ReportExporter(AmazonS3 awsS3Client, EmployeeMapper employeeMapper, EntityManager entityManager) {
        this.awsS3Client = awsS3Client;
        this.employeeMapper = employeeMapper;
        this.entityManager = entityManager;
    }

    /**
     * Export to csv string.
     *
     * @param <T>         the type parameter
     * @param <U>         the type parameter
     * @param reportClass the report class
     * @param dataStream  the data stream
     * @param filePath    the file path
     * @return the string
     * @throws IOException the io exception
     */
    public <T, U> String exportToCsv(Class<T> reportClass, Stream<U> dataStream, String filePath) throws IOException {
        StreamTransferManager streamTransferManager = new StreamTransferManager(awsS3Bucket, filePath, awsS3Client);
        OutputStream outputStream = streamTransferManager.getMultiPartOutputStreams().get(0);
        CsvSchema csvSchema = CsvUtil.buildCsvSchema(reportClass);
        CsvMapper csvMapper = new CsvMapper();
        CsvGenerator csvGenerator = csvMapper.getFactory().createGenerator(outputStream);
        csvGenerator.setSchema(csvSchema);

        dataStream.forEach(data -> {
            try {
                csvGenerator.writeObject(employeeMapper.map((Employee) data));
                entityManager.detach(data);
            } catch (IOException e) {
                throw new EmsBaseException("Something went wrong :: " + e.getMessage());
            }
        });

        outputStream.close();
        streamTransferManager.complete();
        awsS3Client.setObjectAcl(awsS3Bucket, filePath, CannedAccessControlList.PublicRead);
        URL url = awsS3Client.getUrl(awsS3Bucket, filePath);
        return url.toString();
    }

    /**
     * Export to csv string.
     *
     * @param <T>         the type parameter
     * @param <U>         the type parameter
     * @param reportClass the report class
     * @param dataList    the data
     * @param filePath    the file path
     * @return the string
     * @throws IOException the io exception
     */
    public <T, U> String exportToCsv(Class<T> reportClass, List<U> dataList, String filePath) throws IOException {
        File file = new File(filePath);
        FileOutputStream fileOutputStream = new FileOutputStream(file);
        CsvSchema csvSchema = CsvUtil.buildCsvSchema(reportClass);
        CsvMapper csvMapper = new CsvMapper();
        CsvGenerator csvGenerator = csvMapper.getFactory().createGenerator(fileOutputStream);
        csvGenerator.setSchema(csvSchema);

        dataList.forEach(data -> {
            try {
                csvGenerator.writeObject(employeeMapper.map((Employee) data));
            } catch (IOException e) {
                throw new EmsBaseException("Something went wrong :: " + e.getMessage());
            }
        });
        fileOutputStream.close();
        awsS3Client.putObject(new PutObjectRequest(awsS3Bucket, filePath, file).withCannedAcl(CannedAccessControlList.PublicRead));
        URL url = awsS3Client.getUrl(awsS3Bucket, filePath);
        return url.toString();
    }
}
