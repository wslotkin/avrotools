package avrotools;

import java.io.IOException;

public class FullJsonSerializationMain {
    public static void main(String[] args) throws IOException {
        JsonDataWriter.main(args);
        JsonDataReader.main(args);
    }
}
