package avrotools.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;

public class AvroMapperProvider {
    public static ObjectMapper createAvroMapper() {
        ObjectMapper objectMapper = new AvroMapper();

        objectMapper.registerModule(new EntrySetMapModule());

        return objectMapper;
    }
}
