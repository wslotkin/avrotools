package avrotools;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.immutables.value.Value;

@Value.Immutable
@JsonSerialize(as = ImmutableOldUser.class)
@JsonDeserialize(as = ImmutableOldUser.class)
public interface OldUser extends BaseUser {
}
