package mypackage.ddb;

import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import lombok.experimental.Delegate;
import software.amazon.awssdk.services.dynamodb.model.AttributeValue;

import java.util.Map;

@RequiredArgsConstructor
@ToString
public class Attributes implements Map<String, AttributeValue> {
    @Delegate
    private final Map<String, AttributeValue> map;

    public static Attributes from(Map<String, AttributeValue> map) {
        return new Attributes(map);
    }
}
