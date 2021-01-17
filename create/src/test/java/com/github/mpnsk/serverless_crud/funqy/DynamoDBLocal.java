package com.github.mpnsk.serverless_crud.funqy;

import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import java.net.URI;

class DynamoDBLocal {

    public static DynamoDbClient create() {
        return DynamoDbClient.builder()
                .endpointOverride(URI.create("http://localhost:8000"))
                .httpClient(UrlConnectionHttpClient.create())
                .build();
    }
}