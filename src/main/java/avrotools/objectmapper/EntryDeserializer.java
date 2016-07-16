package avrotools.objectmapper;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TreeTraversingParser;

import java.io.IOException;
import java.util.AbstractMap;
import java.util.Map;

class EntryDeserializer<K, V> extends JsonDeserializer<Map.Entry<K, V>> {

    private final Class<K> keyClass;
    private final Class<V> valueClass;

    public EntryDeserializer(Class<K> keyClass, Class<V> valueClass) {
        this.keyClass = keyClass;
        this.valueClass = valueClass;
    }

    @Override
    public Map.Entry<K, V> deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        TreeNode treeNode = p.getCodec().readTree(p);

        K key = readField(ctxt, treeNode, "key", keyClass);
        V value = readField(ctxt, treeNode, "value", valueClass);
        return new AbstractMap.SimpleEntry<>(key, value);
    }

    private <T> T readField(DeserializationContext ctxt, TreeNode treeNode, String name, Class<T> type) throws IOException {
        TreeTraversingParser traversingParser = new TreeTraversingParser((JsonNode) treeNode.get(name));
        traversingParser.nextToken();
        return ctxt.readValue(traversingParser, type);
    }
}
