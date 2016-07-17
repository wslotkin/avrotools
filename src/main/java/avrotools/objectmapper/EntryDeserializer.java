package avrotools.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

class EntryDeserializer extends JsonDeserializer<Map.Entry<?, ?>> {

    private final JavaType keyType;
    private final JavaType valueType;

    public EntryDeserializer(JavaType keyType, JavaType valueType) {
        this.keyType = keyType;
        this.valueType = valueType;
    }

    @Override
    public Map.Entry<?, ?> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        ObjectCodec codec = p.getCodec();
        TreeNode treeNode = codec.readTree(p);

        Object key = readField(ctxt, treeNode, "key", keyType, codec);
        Object value = readField(ctxt, treeNode, "value", valueType, codec);
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private Object readField(DeserializationContext ctxt, TreeNode treeNode, String name, JavaType type, ObjectCodec codec) throws IOException {
        TreeTraversingParser traversingParser = new TreeTraversingParser((JsonNode) treeNode.get(name));
        traversingParser.setCodec(codec);
        traversingParser.nextToken();
        JsonDeserializer<Object> rootValueDeserializer = ctxt.findRootValueDeserializer(type);
        return rootValueDeserializer.deserialize(traversingParser, ctxt);
    }
}
