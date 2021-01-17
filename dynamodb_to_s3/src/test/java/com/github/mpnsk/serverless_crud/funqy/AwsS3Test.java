package com.github.mpnsk.serverless_crud.funqy;

import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.ListObjectsRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import javax.inject.Inject;
import java.util.UUID;

@Disabled
@QuarkusTest
public class AwsS3Test {
    @Inject
    S3Client s3;
    @ConfigProperty(name = "bucket.name")
    String bucketName;
    private final static String TEMP_DIR = System.getProperty("java.io.tmpdir");
    private String key = "testfile.txt";
    private String contentType = "text/plain";

    @Test
    void put() {

        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(bucketName)
                .key(key)
                .contentType(contentType)
                .build();

        s3.putObject(putObjectRequest, RequestBody.fromString("this is file content"));
    }

//    @Test
    void list() {
//        ListObjectsRequest listRequest = ListObjectsRequest.builder().bucket(bucketName).build();
    }


//    @Test
    void get() {
//        GetObjectResponse object = s3.getObject(buildGetRequest(objectKey), ResponseTransformer.toOutputStream(baos));
    }

//        return new File(TEMP_DIR, new StringBuilder().append("s3AsyncDownloadedTemp")
//                .append((new Date()).getTime()).append(UUID.randomUUID())
//            .append(".").append(".tmp").toString());
}
