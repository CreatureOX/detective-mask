package com.github.creatureox.detectivemask.utils;

import com.github.creatureox.detectivemask.config.MaskProperties;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.URL;
import java.util.*;

/**
 * @author CreatureOX
 * version: 1.0
 * date: 2022/4/25
 * description:
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class PropertyUtil {

    private static final String PREFIX = "detective-mask.";

    private static final ClassLoader CLASS_LOADER = PropertyUtil.class.getClassLoader();

    private static final Set<String> PROPERTIES_FILENAMES = new HashSet<>();

    static {
        URL resourceRoot = CLASS_LOADER.getResource("");
        assert resourceRoot != null;
        File[] resources = new File(resourceRoot.getPath()).listFiles();
        assert resources != null;
        for (File resource: resources) {
            if (resource.isDirectory()) {
                continue;
            }
            if (resource.getName().endsWith(".properties")) {
                PROPERTIES_FILENAMES.add(resource.getName());
            }
        }
    }

    public static Map<String, String> loadProperties(String filename) throws IOException {
        Properties properties = new Properties();
        try(InputStream inputStream = CLASS_LOADER.getResourceAsStream(filename)) {
            properties.load(inputStream);
        }
        Map<String, String> propertyMap = new HashMap<>();
        for (String propertyName: properties.stringPropertyNames()) {
            if (propertyName.startsWith(PREFIX)) {
                propertyMap.put(propertyName, properties.getProperty(propertyName));
            }
        }
        return propertyMap;
    }

    public static Map<String, String> loadProperties(Set<String> filenames) throws IOException {
        Map<String, String> propertyMap = new HashMap<>();
        for (String filename: filenames) {
            propertyMap.putAll(loadProperties(filename));
        }
        return propertyMap;
    }

    @SuppressWarnings("rawtypes")
    @SneakyThrows({IllegalArgumentException.class,IllegalAccessException.class,IOException.class})
    public static MaskProperties bind(Class clazz) {
        Map<String, String> properties = loadProperties(PROPERTIES_FILENAMES);
        MaskProperties maskProperties = new MaskProperties();
        String className = clazz.getSimpleName();
        Field[] fields = maskProperties.getClass().getDeclaredFields();
        for (Field field: fields) {
            field.setAccessible(true);
            field.set(maskProperties, properties.get(PREFIX + className + "." + field.getName()));
        }
        return maskProperties;
    }

}
