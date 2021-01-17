package com.github.mpnsk.serverless_crud.funqy;

import io.quarkus.funqy.Funq;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.inject.Inject;
import java.util.Map;

public class GreetingFunction {
    @Inject
    S3Client s3Client;

    @Funq
    public String myFunqyGreeting(Map<String, Object> input) {
        System.out.println("input = " + input);

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket("mpnsk-unversioned")
                .key("abc.txt")
                .contentType("text/plain")
                .build();

        PutObjectResponse future = s3Client.putObject(
                objectRequest,
                RequestBody.fromString("abc-content"));

        return "Hello";
    }
}
