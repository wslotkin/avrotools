package avrotools;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

import java.util.HashMap;
import java.util.Map;

@Value.Immutable
@JsonSerialize(as = ImmutableOldUser.class)
@JsonDeserialize(as = ImmutableOldUser.class)
public interface OldUser extends BaseUser {
    static ImmutableOldUser defaultUser() {
        Map<Double, String> values = new HashMap<>();
        values.put(2.0, "key2");
        return avrotools.ImmutableOldUser.builder().username("myUsername")
                .password("myPassword")
                .age(22.0)
                .putProperties(1, "myFirstProperty")
                .putNestedProperties(2, values)
                .build();
    }
}
