package avrotools;

import java.io.IOException;

public class FullAvroSerializationMain {

    public static void main(String[] args) throws IOException {
        AvroDataWriter.main(args);
        AvroDataReader.main(args);
    }
}
