package avrotools.objectmapper;

import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Map;

class EntrySetMapModule extends SimpleModule {
    public EntrySetMapModule() {
        addSerializer(Map.class, new EntrySetMapSerializer());
        addSerializer(Map.Entry.class, new EntrySerializer());
        setDeserializerModifier(new MapDeserializerModifier());
    }
}
