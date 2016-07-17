package avrotools.objectmapper;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.cfg.MapperConfig;
import com.fasterxml.jackson.databind.introspect.AnnotatedMethod;

import java.lang.reflect.Field;

class PrefixPropertyNamingStrategy extends PropertyNamingStrategy {

    private static final String PREFIX = "the";

    @Override
    public String nameForSetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return prependPrefixIfNeeded(config, method, defaultName);
    }

    @Override
    public String nameForGetterMethod(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return prependPrefixIfNeeded(config, method, defaultName);
    }

    private static String prependPrefixIfNeeded(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return usesPropertyPrefix(method.getDeclaringClass())
                ? prependPrefix(config, method, defaultName)
                : defaultName;
    }

    private static String prependPrefix(MapperConfig<?> config, AnnotatedMethod method, String defaultName) {
        return PREFIX + UPPER_CAMEL_CASE.nameForGetterMethod(config, method, defaultName);
    }

    private static boolean usesPropertyPrefix(Class<?> type) {
        Field[] declaredFields = type.getDeclaredFields();
        return declaredFields.length == 0 || declaredFields[0].getName().startsWith(PREFIX);
    }
}
