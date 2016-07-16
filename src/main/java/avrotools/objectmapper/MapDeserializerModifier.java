package avrotools.objectmapper;

import com.fasterxml.jackson.databind.BeanDescription;
import com.fasterxml.jackson.databind.DeserializationConfig;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.deser.BeanDeserializerModifier;
import com.fasterxml.jackson.databind.type.MapType;

import java.util.List;
import java.util.Map;

import static avrotools.objectmapper.EntrySetMapDeserializer.mapDeserializer;

class MapDeserializerModifier extends BeanDeserializerModifier {
    @Override
    public JsonDeserializer<?> modifyMapDeserializer(DeserializationConfig config, MapType type, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        return mapDeserializer(type, config.getTypeFactory());
    }

    @Override
    public JsonDeserializer<?> modifyDeserializer(DeserializationConfig config, BeanDescription beanDesc, JsonDeserializer<?> deserializer) {
        JavaType type = beanDesc.getType();
        if (type.isTypeOrSubTypeOf(Map.Entry.class)) {
            List<JavaType> typeParameters = type.getBindings().getTypeParameters();
            return new EntryDeserializer<>(typeParameters.get(0).getRawClass(), typeParameters.get(1).getRawClass());
        } else {
            return super.modifyDeserializer(config, beanDesc, deserializer);
        }
    }
}
