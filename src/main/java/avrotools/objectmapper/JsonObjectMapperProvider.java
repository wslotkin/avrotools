package avrotools.objectmapper;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonObjectMapperProvider {
    public static ObjectMapper createObjectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.registerModule(new EntrySetMapModule());
        objectMapper.setPropertyNamingStrategy(new PrefixPropertyNamingStrategy());

        return objectMapper;
    }
}
