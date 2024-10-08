package com.socialmedia.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class S3Config {

    @Value("${cloud.aws.credentials.access.key}")
    @Getter
    private String accessKeyId;

    @Value("${cloud.aws.credentials.secret-key}")
    @Getter
    private String accessKeySecret;

    @Value("${cloud.aws.region.static}")
    @Getter
    private String bucketRegion;

    @Bean
    public AmazonS3 getAwsS3Client() {
    	BasicAWSCredentials basicAWSCredentials = new BasicAWSCredentials(accessKeyId, accessKeySecret);
        return AmazonS3ClientBuilder
                .standard()
                .withCredentials(new AWSStaticCredentialsProvider(basicAWSCredentials))
                .withRegion(bucketRegion)
                .build();
    }
}

