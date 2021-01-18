package com.github.mpnsk.serverless_crud.funqy;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.mpnsk.serverless_crud.Game;
import com.github.mpnsk.serverless_crud.GameService;
import io.quarkus.funqy.Funq;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectResponse;

import javax.inject.Inject;
import java.util.List;
import java.util.Map;

public class DdbToS3Function {
    @Inject
    S3Client s3Client;

    @Inject
    GameService gameService;

    @Inject
    ObjectMapper objectMapper;

    @Funq
    public String ddbToS3(Map<String, Object> input) {
        System.out.println("input = " + input);

        List<Game> games = gameService.findAll();
        String jsonString = "";
        try {
            jsonString = objectMapper.writeValueAsString(games);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        System.out.println("jsonString = " + jsonString);

        PutObjectRequest objectRequest = PutObjectRequest.builder()
                .bucket("mpnsk-unversioned")
                .key("abc.json")
                .contentType("application/json")
                .build();

        PutObjectResponse response = s3Client.putObject(
                objectRequest,
                RequestBody.fromString(jsonString));

        return jsonString;
    }
}
