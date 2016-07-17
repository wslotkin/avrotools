package avrotools;

import avrotools.external.ExternalDatamodel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static avrotools.OldUser.defaultUser;
import static avrotools.objectmapper.JsonObjectMapperProvider.createObjectMapper;

public class JsonDataWriter {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = createObjectMapper();

        objectMapper.writeValue(new File("userdata.json"), defaultUser());
        objectMapper.writeValue(new File("externaldata.json"), new ExternalDatamodel("1", 2));
    }
}
