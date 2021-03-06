package avrotools;

import org.apache.avro.Schema;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;

import static avrotools.OldUser.defaultUser;

public class AvroDataWriter {

    public static void main(String[] args) throws IOException {
        writeToAvro(defaultUser());
    }

    private static void writeToAvro(ImmutableOldUser user) throws IOException {
        System.out.println("Serializing user to avro: " + user);
        Schema schema = ReflectData.get().getSchema(ImmutableOldUser.class);
        DatumWriter<ImmutableOldUser> userDatumWriter = new ReflectDatumWriter<>(ImmutableOldUser.class);
        DataFileWriter<ImmutableOldUser> dataFileWriter = new DataFileWriter<>(userDatumWriter);
        dataFileWriter.create(schema, new File("users.avro"));
        dataFileWriter.append(user);
        dataFileWriter.flush();
        dataFileWriter.close();
        System.out.println("done\n");
    }
}
