package avrotools;

import avro.shaded.com.google.common.collect.ImmutableMap;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOldUser.class)
@JsonDeserialize(as = ImmutableOldUser.class)
public interface OldUser extends BaseUser {
    static ImmutableOldUser defaultUser() {
        return avrotools.ImmutableOldUser.builder().username("myUsername")
                .password("myPassword")
                .age(22.0)
                .putProperties(1, "myFirstProperty")
                .putNestedProperties(2, ImmutableMap.of(2.0, "key2"))
                .putStringMap("myString", 3)
                .putNestedStringMap(5, ImmutableMap.of("mySecondLevelKey", 6.0))
                .build();
    }
}
