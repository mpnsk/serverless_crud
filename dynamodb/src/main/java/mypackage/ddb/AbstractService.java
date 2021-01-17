package mypackage.ddb;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.GetItemRequest;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.services.dynamodb.model.ScanRequest;

import java.util.Collection;
import java.util.Map;
import java.util.function.Function;
import java.util.logging.LogManager;
import java.util.logging.Logger;

@RequiredArgsConstructor
public abstract class AbstractService<T> {

    private static final Logger LOG = LogManager.getLogManager().getLogger(AbstractService.class.getName());

    final String tableName;
    final String keyColumn;
    final Function<T, Attributes> to;
    final Collection<String> columns;

    public String getTableName() {
        return tableName;
    }

    protected ScanRequest scanRequest() {
        return ScanRequest.builder().tableName(getTableName())
                .attributesToGet(columns).build();
    }

    protected PutItemRequest putRequest(T t) {
        Attributes apply = to.apply(t);
        String message = "Attributes = " + apply;
        System.out.println(message);
        LOG.info(message);
        PutItemRequest build = PutItemRequest.builder()
                .tableName(getTableName())
                .item(apply)
                .build();
        String message1 = "PutItemRequest = " + build;
        LOG.info(message1);
        System.out.println(message1);
        return build;
    }

    protected GetItemRequest getRequest(String id) {
        return GetItemRequest.builder()
                .tableName(getTableName())
                .key(Map.of(keyColumn, AttributeValue.builder().s(id).build()))
                .attributesToGet(columns)
                .build();
    }
}