package avrotools;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.apache.avro.reflect.Nullable;
import org.immutables.value.Value;

@Value.Immutable
@Value.Style(privateNoargConstructor = true)
@JsonSerialize(as = avrotools.ImmutableUser.class)
@JsonDeserialize(as = avrotools.ImmutableUser.class)
public interface User {
    String getUsername();

    String getPassword();

    double getAge();

    @Value.Default
    @Nullable
    default Double getNewField() {
        return 42.0;
    }
}
