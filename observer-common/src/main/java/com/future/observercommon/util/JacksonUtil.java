package com.future.observercommon.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Json工具类
 */
public final class JacksonUtil {

    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * 获取json字符串中的指定属性对应的JsonNode
     */
    public static JsonNode jsonNodeOf(String json, String... fields) throws JsonProcessingException {
        JsonNode jsonNode = mapper.readTree(json);

        for (String field : fields) {
            jsonNode = jsonNode.path(field);
        }

        return jsonNode;
    }

    /**
     * 获取JsonNode中的指定属性对应的JsonNode
     */
    public static JsonNode jsonNodeOf(JsonNode jsonNode, String... fields) {
        for (String field : fields) {
            jsonNode = jsonNode.path(field);
        }

        return jsonNode;
    }

    /**
     * 将java对象转换为json字符串
     */
    public static String toJson(Object value) throws JsonProcessingException {
        return mapper.writeValueAsString(value);
    }

    /**
     * 将json字符串转换为Map集合
     */
    public static <T> T fromJson(String json, Class<T> classOfT) throws JsonProcessingException {
        return mapper.readValue(json, classOfT);
    }
}
