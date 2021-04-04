package com.k3v007.databaseS3Pipeline.configuration;

import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Amazon s3 config.
 *
 * @author Vivek
 */
@Configuration
public class AmazonS3Config {

    @Value("${aws.s3.region}")
    private String awsRegion;

    /**
     * Amazon s3 client amazon s3.
     *
     * @return the amazon s3
     */
    @Bean
    public AmazonS3 amazonS3Client() {
        return AmazonS3ClientBuilder.standard()
                .withRegion(awsRegion)
                .build();
    }
}
