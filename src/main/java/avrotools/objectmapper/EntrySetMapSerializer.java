package avrotools.objectmapper;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.util.Map;

class EntrySetMapSerializer extends JsonSerializer<Map> {
    @Override
    public void serialize(Map value, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeObject(value.entrySet());
    }
}
