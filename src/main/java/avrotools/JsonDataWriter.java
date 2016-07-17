package avrotools;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import static avrotools.OldUser.defaultUser;
import static avrotools.objectmapper.JsonObjectMapperProvider.createObjectMapper;

public class JsonDataWriter {

    public static void main(String[] args) throws IOException {
        ObjectMapper objectMapper = createObjectMapper();

        byte[] bytes = objectMapper.writeValueAsBytes(defaultUser());

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        byteArrayOutputStream.write(bytes);
        byteArrayOutputStream.writeTo(new FileOutputStream(new File("userdata.json")));
    }
}
