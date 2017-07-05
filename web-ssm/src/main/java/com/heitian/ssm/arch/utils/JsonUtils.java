package com.heitian.ssm.arch.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

/**
 * json 工具类
 *
 * @author 宋俊杰
 */
public class JsonUtils {
    /**
     * 默认日期格式
     */
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm:ss";

    private static ObjectMapper defaultObjectMapper = getObjectMapper(null);

    /**
     * 返回一个Object对象
     * @param datePattern，指定能够处理的日期字符串格式，如果没有指定默认为yyyy-MM-dd HH:mm:ss
     * @return
     */
    public static ObjectMapper getObjectMapper(String datePattern) {
        if (StringUtils.isBlank(datePattern)) {
            datePattern = DATE_PATTERN;
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat(datePattern));
        //允许字段名字不使用引号
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_CONTROL_CHARS, true);
        //允许单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 忽略未识别的参数(解决json串中多属性，而类中又未定义的情况)
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //将对象转换成string时，json的key是否按照对象的field的自然顺序排列
        objectMapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, false);
        return objectMapper;
    }

    /**
     * 将规定对象转换成json字符串。日期类型按照 yyyy-MM-dd HH:mm:ss 的格式转换成字符串
     * @param obj 对象
     * @return json字符串
     */
    public static String writeValueAsString(Object obj){
        try {
            return getObjectMapper(null).writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将规定对象转换成json字符串
     * @param object
     * @param datePattern 指定转换后的日期格式
     * @return
     */
    public static String writeValueAsString(Object object, String datePattern) {
        try {
            return getObjectMapper(datePattern).writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将规定对象转换成json字符串
     * @param object
     * @param datePattern 指定转换后的日期格式
     * @param indentOutput 缩进输出
     * @return
     */
    public static String writeValueAsString(Object object, String datePattern, boolean indentOutput) {
        try {
            ObjectMapper objectMapper = getObjectMapper(datePattern);
            objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
            return objectMapper.writeValueAsString(object);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 将json字符串转为对象
     * @param <T> 返回类型
     * @param content json字符串
     * @param valueType 返回类型
     * @return 对象
     */
    public static <T> T readValue(String content, Class<T> valueType) {
        try {
            ObjectMapper objectMapper = getObjectMapper(null);
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json转换成对象
     * @param content
     * @param valueTypeRef
     * @return
     */
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef){
        try {
            ObjectMapper objectMapper = getObjectMapper(null);
            return objectMapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json转换成对象
     * @param content
     * @param valueTypeRef
     * @param dateStyle
     * @return
     */
    public static <T> T readValue(String content, TypeReference<T> valueTypeRef, String dateStyle){
        try {
            ObjectMapper objectMapper = getObjectMapper(dateStyle);
            return objectMapper.readValue(content, valueTypeRef);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 将json字符串转为对象，日期类型按照给定的样式转换
     *
     * @param <T> 返回类型
     * @param content json字符串
     * @param valueType 返回类型
     * @param dateStyle 日期格式
     * @return 对象
     */
    public static <T> T readValue(String content, Class<T> valueType, String dateStyle) {
        try {
            ObjectMapper objectMapper = getObjectMapper(dateStyle);
            return objectMapper.readValue(content, valueType);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 根据所给的json字符串转换 JsonNode 对象
     * @param content
     * @return
     */
    public static JsonNode readTree(String content){
        try {
            ObjectMapper objectMapper = getObjectMapper(null);
            return objectMapper.readTree(content);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    /**
     * 根据给定的路径，返回对应的 JsonNode对象
     * @param jsonNode
     * @param path 路径表达式。 例如：对于{param:{transCode:"123"}} 中的transCode可以这样表达,getJsonNode(jsonNode,"param.transCode")
     * @return
     */
    public static JsonNode getJsonNode(JsonNode jsonNode , String path){
        String[] pathArr = path.split("\\.");
        JsonNode temp = jsonNode;
        for(String filed : pathArr){
            temp = temp.get(filed);
        }
        return temp;
    }

    /**
     * 根据给定的路径，返回对应的 JsonNode对象
     * @param json
     * @param path
     * @return
     */
    public static JsonNode getJsonNode(String json , String path){
        JsonNode root = readTree(json);
        return getJsonNode(root,path);
    }

    /**
     * 得到给定属性的值
     * @param jsonNode
     * @param path
     * @return
     */
    public static String getFieldValue(JsonNode jsonNode, String path){
        JsonNode node = getJsonNode(jsonNode,path);
        if(node==null){
            return null;
        }
        return node.asText();
    }

    public static String getFieldValue(String json , String path){
        JsonNode jsonNode = readTree(json);
        return getFieldValue(jsonNode,path);
    }

    public static void main(String[] args){
//		Map map = new HashMap();
//		map.put("name", "张三");
//		map.put("age", 50);
//		map.put("生日", new Date());
//		System.out.println(writeValueAsString(map));
//		System.out.println(writeValueAsString(map,"yyyy年MM月dd日HH时mm分"));

        String str = "{'username':'admin',password:'000000'}";

//		String str = "{ \"name\" : \"萧远山\", \"sex\" : \"男\", \"age\" : \"23\",\"address\" : \"河南郑州\"}";
        Map<?,?> map = readValue(str,HashMap.class);
        System.out.println(map);
    }
}
