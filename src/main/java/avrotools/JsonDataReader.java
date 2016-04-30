package avrotools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class JsonDataReader {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();

        Object myDeserializedData = objectMapper.readerFor(User.class).readValue(new File("userdata.json"));

        System.out.println(myDeserializedData);
    }
}
