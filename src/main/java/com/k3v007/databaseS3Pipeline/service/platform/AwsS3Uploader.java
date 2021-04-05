package com.k3v007.databaseS3Pipeline.service.platform;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

/**
 * The type Aws s3 uploader.
 *
 * @author Vivek
 */
@Service
@Slf4j
public class AwsS3Uploader {

    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;

    private final AmazonS3 awsS3Client;

    /**
     * Instantiates a new Aws s 3 uploader.
     *
     * @param awsS3Client the aws s 3 client
     */
    @Autowired
    public AwsS3Uploader(AmazonS3 awsS3Client) {
        this.awsS3Client = awsS3Client;
    }

    /**
     * Upload file to s 3 string.
     *
     * @param key      the key
     * @param fileName the file name
     * @param file2    the file 2
     * @return the string
     */
    public String uploadFileToS3(String key, String fileName, byte[] file2) {
        try {
            File file = convertMultiPartToFile(fileName, file2);
            String fileUrl = uploadFileAndGetUrl(file, key);
            log.info("Upload file Done with file name {}", fileName);
            file.delete();
            return fileUrl;
        } catch (Exception e) {
            log.error("Upload file failed with exception: " + e.getStackTrace());
        }
        return null;
    }

    private File convertMultiPartToFile(String fileName, byte[] file2) {
        File convFile = new File(fileName);
        FileOutputStream fos;
        try {
            fos = new FileOutputStream(convFile);
            fos.write(file2);
            fos.close();
        } catch (FileNotFoundException e) {
            log.error("Upload file failed with exception: ", e.getStackTrace());
        } catch (IOException e) {
            log.error("Upload file failed with exception: " + e.getStackTrace());
        }
        return convFile;
    }

    private String uploadFileAndGetUrl(File file, String keyName) {
        try {
            awsS3Client.putObject(new PutObjectRequest(awsS3Bucket, keyName, file));
            URL url = awsS3Client.getUrl(awsS3Bucket, keyName);
            return url.toString();
        } catch (AmazonServiceException e) {
            System.err.println(e.getErrorMessage());
            log.error("Failed to upload file to S3 with exception: " + e.getErrorMessage());
        }
        return null;
    }
}
