package avrotools;

import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.reflect.ReflectDatumReader;

import java.io.File;
import java.io.IOException;

public class AvroDataReader {

    public static void main(String[] args) throws IOException {
        readAvro();
    }

    private static void readAvro() throws IOException {
        DatumReader<ImmutableUser> userDatumReader = new ReflectDatumReader<>(ImmutableUser.class);
        DataFileReader<ImmutableUser> dataFileReader = new DataFileReader<>(new File("users.avro"), userDatumReader);
        User user;
        while (dataFileReader.hasNext()) {
            user = dataFileReader.next(null);
            System.out.println(user);
        }
    }
}
