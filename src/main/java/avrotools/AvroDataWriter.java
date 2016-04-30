package avrotools;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;

public class AvroDataWriter {

    public static void main(String[] args) throws IOException {
        ImmutableUser user = avrotools.ImmutableUser.builder().username("myUsername")
                .password("myPassword")
                .age(22.0)
                .build();
        writeToAvro(user);
    }

    private static void writeToAvro(ImmutableUser user) throws IOException {
        Schema schema = ReflectData.get().getSchema(ImmutableUser.class);
        DatumWriter<ImmutableUser> userDatumWriter = new ReflectDatumWriter<>(ImmutableUser.class);
        DataFileWriter<ImmutableUser> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(schema, new File("users.avro"));
        dataFileWriter.append(user);
        dataFileWriter.flush();
        dataFileWriter.close();
    }
}
