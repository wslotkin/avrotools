package avrotools.objectmapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;

public class AvroMapperProvider {
    public static ObjectMapper createAvroMapper() {
        ObjectMapper objectMapper = new AvroMapper();

        objectMapper.registerModule(new EntrySetMapModule());
        objectMapper.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);

        return objectMapper;
    }
}
