package avrotools.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.type.TypeFactory;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

import static java.util.stream.Collectors.toMap;

class EntrySetMapDeserializer<K, V> extends JsonDeserializer<Map<K, V>> {

    private final JavaType entrySetType;

    public static <K, V> EntrySetMapDeserializer<K, V> mapDeserializer(JavaType mapType, TypeFactory typeFactory) {
        JavaType entryType = typeFactory.constructParametricType(Map.Entry.class, mapType.getKeyType(), mapType.getContentType());
        return new EntrySetMapDeserializer<>(typeFactory.constructCollectionType(Set.class, entryType));
    }

    private EntrySetMapDeserializer(JavaType entrySetType) {
        this.entrySetType = entrySetType;
    }

    @Override
    public Map<K, V> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Set<Map.Entry<K, V>> entrySet = ctxt.readValue(p, entrySetType);

        return entrySet.stream().collect(toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
