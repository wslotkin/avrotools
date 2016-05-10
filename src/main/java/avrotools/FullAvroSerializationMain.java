package avrotools;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.avro.AvroMapper;
import com.fasterxml.jackson.dataformat.avro.AvroSchema;
import org.apache.avro.Schema;
import org.apache.avro.file.DataFileReader;
import org.apache.avro.file.DataFileWriter;
import org.apache.avro.io.DatumReader;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.reflect.ReflectData;
import org.apache.avro.reflect.ReflectDatumReader;
import org.apache.avro.reflect.ReflectDatumWriter;

import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;

public class FullAvroSerializationMain {

    public static void main(String[] args) throws IOException {
        ImmutableOldUser persistedUser = avrotools.ImmutableOldUser.builder().username("myUsername")
                .password("myPassword")
                .age(22.0)
                .build();

        writeToAvro(persistedUser);

        readAvroViaJackson();
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

    private static void readAvroViaJackson() throws IOException {
        System.out.println("Deserializing avro via jackson");
        DatumReader<ImmutableUser> userDatumReader = new ReflectDatumReader<>(ImmutableUser.class);
        DataFileReader<ImmutableUser> dataFileReader = new DataFileReader<>(new File("users.avro"), userDatumReader);

        AvroSchema persistedSchema = new AvroSchema(dataFileReader.getSchema());
        ObjectReader reader = new AvroMapper().readerFor(User.class).with(persistedSchema);
        while (dataFileReader.hasNext()) {
            ByteBuffer byteBuffer = dataFileReader.nextBlock();
            Object user = reader.readValue(byteBuffer.array());
            System.out.println(user);
        }
        System.out.println("done\n");
    }
}
