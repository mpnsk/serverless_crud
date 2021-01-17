package com.github.mpnsk.serverless_crud;
import lombok.experimental.Delegate;
import mypackage.ddb.Attributes;
import mypackage.ddb.SyncService;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class GameService {

    @Delegate
    SyncService<Game> syncService;

    @Inject
    public GameService(DynamoDbClient dynamoDbClient) {
        syncService = new SyncService<>(
                Game.tableName,
                Game.Fields.id.name(),
                attributes -> Game.builder()
                        .id(attributes.get(Game.Fields.id.name()).s())
                        .title(attributes.get(Game.Fields.title.name()).s())
                        .description(attributes.get(Game.Fields.description.name()).s())
                        .year(Integer.parseInt(attributes.get(Game.Fields.year.name()).n()))
                        .build(),
                (Game game) -> Attributes.from(Map.of(
                        Game.Fields.id.name(),
                        AttributeValue.builder().s(game.id).build(),
                        Game.Fields.title.name(),
                        AttributeValue.builder().s(game.title).build(),
                        Game.Fields.description.name(),
                        AttributeValue.builder().s(game.description).build(),
                        Game.Fields.year.name(),
                        AttributeValue.builder().n(String.valueOf(game.year)).build()
                )),
                Arrays.stream(Game.Fields.values()).map(Enum::name).collect(Collectors.toList()),
                dynamoDbClient
        );
    }
}
