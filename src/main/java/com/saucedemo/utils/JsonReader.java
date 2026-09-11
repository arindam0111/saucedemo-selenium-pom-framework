package com.saucedemo.utils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonReader {

    // 1. Single-argument method (Legacy Map version)
    public static Object[][] getJsonData(String filePath) {
        ObjectMapper mapper = new ObjectMapper();
        List<Map<String, String>> dataList;

        try {
            dataList = mapper.readValue(new File(filePath), new TypeReference<List<Map<String, String>>>() {});
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON test data from: " + filePath, e);
        }

        Object[][] data = new Object[dataList.size()][3];
        for (int i = 0; i < dataList.size(); i++) {
            Map<String, String> row = dataList.get(i);
            data[i][0] = row.get("username");
            data[i][1] = row.get("password");
            data[i][2] = row.get("expectedErrorMessage");
        }

        return data;
    }

    // 2. Overloaded generic method (Accepts any POJO class like CheckoutData.class)
    public static <T> Object[][] getJsonData(String filePath, Class<T> clazz) {
        ObjectMapper mapper = new ObjectMapper();
        List<T> dataList;

        try {
            JavaType type = mapper.getTypeFactory().constructCollectionType(List.class, clazz);
            dataList = mapper.readValue(new File(filePath), type);
        } catch (IOException e) {
            throw new RuntimeException("Failed to read JSON test data from: " + filePath, e);
        }

        Object[][] data = new Object[dataList.size()][1];
        for (int i = 0; i < dataList.size(); i++) {
            data[i][0] = dataList.get(i);
        }

        return data;
    }
}