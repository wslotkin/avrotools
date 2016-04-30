package avrotools;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.io.DatumReader;
import org.apache.avro.reflect.ReflectDatumReader;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class AvroDataReader {

    public static void main(String[] args) throws IOException {
        readAvroViaJackson();
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

    private static void readAvroViaJackson() throws IOException {
        DatumReader<ImmutableUser> userDatumReader = new ReflectDatumReader<>(ImmutableUser.class);
        DataFileReader<ImmutableUser> dataFileReader = new DataFileReader<>(new File("users.avro"), userDatumReader);

        AvroSchema persistedSchema = new AvroSchema(dataFileReader.getSchema());
        ObjectReader reader = new AvroMapper().readerFor(User.class).with(persistedSchema);
        while (dataFileReader.hasNext()) {
            ByteBuffer byteBuffer = dataFileReader.nextBlock();
            Object user = reader.readValue(byteBuffer.array());
            System.out.println(user);
        }
    }
}
