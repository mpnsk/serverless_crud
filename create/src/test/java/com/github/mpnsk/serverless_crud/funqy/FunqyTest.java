package com.github.mpnsk.serverless_crud.funqy;

import com.github.mpnsk.serverless_crud.Game;
import com.github.mpnsk.serverless_crud.GameService;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import software.amazon.awssdk.services.dynamodb.model.AttributeDefinition;
import software.amazon.awssdk.services.dynamodb.model.BillingMode;
import software.amazon.awssdk.services.dynamodb.model.CreateTableRequest;
import software.amazon.awssdk.services.dynamodb.model.KeySchemaElement;
import software.amazon.awssdk.services.dynamodb.model.KeyType;
import software.amazon.awssdk.services.dynamodb.model.ScalarAttributeType;

import java.util.List;

@Disabled
@QuarkusTest
@ExtendWith(LocalDbCreationRule.class)
public class FunqyTest {

    @Disabled
    @Test
    public void testSimpleLambdaSuccess() {
        Game game = Game.builder()
                .title("hello world")
                .description("a description")
                .year(123)
                .build();

        createTable();

        var response = new CreateFunction(new GameService(DynamoDBLocal.create())).create(game);

        /*
        List<Game> expected = List.of(Game.builder()
                .id(response.get(0).id)
                .title("hello world")
                .description("a description")
                .year(123)
                .build());
        Assertions.assertIterableEquals(expected, response);
         */
    }


    private void createTable() {
        DynamoDBLocal.create()
                .createTable(CreateTableRequest.builder()
                        .tableName(Game.tableName)
                        .keySchema(KeySchemaElement.builder().attributeName(Game.Fields.id.name()).keyType(KeyType.HASH).build())
                        .attributeDefinitions(AttributeDefinition.builder().attributeName(Game.Fields.id.name()).attributeType(ScalarAttributeType.S).build())
                        .billingMode(BillingMode.PAY_PER_REQUEST)
                        .build());
    }
}
