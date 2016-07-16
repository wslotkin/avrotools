package avrotools.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

class EntrySerializer extends JsonSerializer<Map.Entry> {
    @Override
    public void serialize(Map.Entry value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("key", value.getKey());
        gen.writeObjectField("value", value.getValue());
        gen.writeEndObject();
    }
}
