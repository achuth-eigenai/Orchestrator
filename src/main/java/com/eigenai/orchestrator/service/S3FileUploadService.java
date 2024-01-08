package com.eigenai.orchestrator.service;

import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentialsProvider;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.eigenai.orchestrator.vo.S3FileUploadResponse;
import com.eigenai.orchestrator.util.DateUtil;
import lombok.Builder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import javax.xml.crypto.Data;
import java.io.File;
import java.net.URL;
import java.util.Date;

import static com.amazonaws.HttpMethod.POST;
import static com.amazonaws.HttpMethod.PUT;
import static com.eigenai.orchestrator.util.DateUtil.afterAnHour;

/**
 * S3 File Upload Service.
 */
@Service
@Slf4j
public class S3FileUploadService {

    @Value("${orchestrator.bucket.name}")
    private String bucketName;


    private AmazonS3 amazonS3;
    private DateUtil dateUtil;
    private AWSCredentialsProvider awsCredentialsProvider;


    /**
     * Generate Pre signed Url Request.
     * @param bucketName
     * @param objectKey
     * @param httpMethod
     * @param expiration
     * @return
     */
    private GeneratePresignedUrlRequest getGeneratePreSignedUrlRequest(String bucketName, String objectKey,
                                                                       HttpMethod httpMethod, Date expiration) {
        GeneratePresignedUrlRequest preSignedUrlRequest = new GeneratePresignedUrlRequest(bucketName, objectKey)
                .withMethod(httpMethod)
                .withExpiration(expiration);
        return preSignedUrlRequest;
    }

    /**
     * upload files to s3.
     * @param userId
     * @return
     */
    public S3FileUploadResponse uploadFileToS3(String userId) {
        try {
            Date expiration = afterAnHour();
            AmazonS3 amazonS3Client = AmazonS3ClientBuilder.standard()
                    .withCredentials(awsCredentialsProvider).build();
            URL url = amazonS3Client.generatePresignedUrl(
                    getGeneratePreSignedUrlRequest(bucketName, userId, PUT, expiration));
            return S3FileUploadResponse.builder().url(url.toString()).build();
        } catch (SdkClientException e) {
            throw new RuntimeException(e);
        }
    }

}
