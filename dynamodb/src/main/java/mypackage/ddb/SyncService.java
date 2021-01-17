package mypackage.ddb;

import software.amazon.awssdk.services.dynamodb.DynamoDbClient;
import software.amazon.awssdk.services.dynamodb.model.PutItemResponse;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;


public class SyncService<T> extends AbstractService<T> {

    private final Function<Attributes, T> from;
    private final DynamoDbClient dynamoDB;

    public SyncService(String tableName,
                       String key,
                       Function<Attributes, T> from,
                       Function<T, Attributes> to,
                       Collection<String> columns,
                       DynamoDbClient dynamoDB) {
        super(tableName, key, to, columns);
        this.from = from;
        this.dynamoDB = dynamoDB;
    }

    public List<T> findAll() {
        return dynamoDB.scanPaginator(scanRequest()).items().stream()
                .map(Attributes::from)
                .map(from)
                .collect(Collectors.toList());
    }

    public PutItemResponse add(T item) {
        PutItemResponse putItemResponse = dynamoDB.putItem(putRequest(item));
        return putItemResponse;
    }

    public T get(String name) {
        return from.apply(Attributes.from(dynamoDB.getItem(getRequest(name)).item()));
    }
}