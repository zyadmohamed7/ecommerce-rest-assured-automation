package org.example.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

public class JsonUtils {

    private static final ObjectMapper objectMapper = new ObjectMapper();


    public static <T> T readJsonFile(String filePath, Class<T> valueType) throws IOException {
        InputStream inputStream = getResourceAsStream("data/" + filePath);
        if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
        }
        return objectMapper.readValue(inputStream, valueType);
    }


    public static <T> T readJsonFile(String filePath, TypeReference<T> valueTypeRef) throws IOException {
        InputStream inputStream = getResourceAsStream("data/" + filePath);
        if (inputStream == null) {
            throw new IOException("File not found: " + filePath);
        }
        return objectMapper.readValue(inputStream, valueTypeRef);
    }


    public static void writeJsonFile(String filePath, Object data) throws IOException {
        File file = getResourceAsFile("data/" + filePath);
        objectMapper.writerWithDefaultPrettyPrinter().writeValue(file, data);
    }

    private static InputStream getResourceAsStream(String resourceName) {
        return JsonUtils.class.getClassLoader().getResourceAsStream(resourceName);
    }

    private static File getResourceAsFile(String resourceName) throws IOException {
        URL url = JsonUtils.class.getClassLoader().getResource(resourceName);
        if (url == null) {
            // if resource doesn't exist, create the file path
            String path = "src/test/resources/" + resourceName;
            File file = new File(path);
            file.getParentFile().mkdirs();
            return file;
        }
        return new File(url.getFile());
    }
}
