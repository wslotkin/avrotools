package avrotools;

import avrotools.external.ExternalDatamodel;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

import static avrotools.objectmapper.JsonObjectMapperProvider.createObjectMapper;

public class JsonDataReader {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = createObjectMapper();

        Object myDeserializedData = objectMapper.readerFor(User.class).readValue(new File("userdata.json"));

        System.out.println(myDeserializedData);

        Object myDeserializedData2 = objectMapper.readerFor(ExternalDatamodel.class).readValue(new File("externaldata.json"));

        System.out.println(myDeserializedData2);
    }
}
