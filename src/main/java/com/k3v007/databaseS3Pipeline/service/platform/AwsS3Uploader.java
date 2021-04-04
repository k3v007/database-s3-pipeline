package com.k3v007.databaseS3Pipeline.service.platform;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.k3v007.databaseS3Pipeline.exception.EmsBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;

/**
 * The type Aws s3 uploader.
 *
 * @author Vivek
 */
@Service
public class AwsS3Uploader {

    @Value("${aws.s3.bucket}")
    private String awsS3Bucket;

    private final AmazonS3 amazonS3;

    @Autowired
    public AwsS3Uploader(AmazonS3 amazonS3) {
        this.amazonS3 = amazonS3;
    }

    public String uploadFile(MultipartFile multipartFile) {
        File file = convertMultipartToFile(multipartFile);
        String fileName = generateFileName(multipartFile);
        URL fileUrl = uploadFile(file, fileName);
        return fileUrl.toExternalForm();
    }

    private File convertMultipartToFile(MultipartFile file) {
        File convertedFile = new File(Objects.requireNonNull(file.getOriginalFilename()));
        try (FileOutputStream fos = new FileOutputStream(convertedFile)) {
            fos.write(file.getBytes());
        } catch (IOException e) {
            throw new EmsBaseException("Unable to Handle File Conversion!");
        }
        return convertedFile;
    }

    private String generateFileName(MultipartFile multiPart) {
        return new Date().getTime() + "-" + multiPart.getOriginalFilename();
    }

    private URL uploadFile(File file, String fileName) {
        amazonS3.putObject(new PutObjectRequest(awsS3Bucket, fileName, file)
                .withCannedAcl(CannedAccessControlList.PublicRead));
        file.delete();
        return amazonS3.getUrl(awsS3Bucket, fileName);
    }
}
