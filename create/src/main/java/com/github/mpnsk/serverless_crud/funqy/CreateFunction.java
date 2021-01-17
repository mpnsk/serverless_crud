package com.github.mpnsk.serverless_crud.funqy;

import com.github.mpnsk.serverless_crud.Game;
import com.github.mpnsk.serverless_crud.GameService;
import io.quarkus.funqy.Funq;
import software.amazon.awssdk.http.urlconnection.UrlConnectionHttpClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.PublishRequest;


import javax.inject.Inject;
import java.util.UUID;

public class CreateFunction {

    private final GameService gameService;
    private String topicArn = "place-topic-arn-here i'll inject it as property later";

    @Inject
    public CreateFunction(GameService gameService) {
        this.gameService = gameService;
    }

    @Funq
    public String create(Game game) {
        Game withId = game.id == null || game.id.isEmpty() ?
                game.withId(UUID.randomUUID().toString()) :
                game;
        PutItemResponse putItemResponse = gameService.add(withId);
        PublishRequest request = PublishRequest.builder()
                .message(withId.id)
                .topicArn(topicArn)
                .build();
        SnsClient snsClient = SnsClient.builder().httpClient(UrlConnectionHttpClient.create()).build();
        snsClient.publish(request);
        String toString = putItemResponse.attributes().toString();
        System.out.println("toString = " + toString);
        return toString;
    }
}
