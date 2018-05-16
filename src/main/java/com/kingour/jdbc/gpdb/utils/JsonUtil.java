package com.kingour.jdbc.gpdb.utils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.parser.ParserConfig;
import com.alibaba.fastjson.parser.deserializer.DateDeserializer;
import com.alibaba.fastjson.serializer.SerializeFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;

public class JsonUtil {

    @SuppressWarnings("unchecked")
    public static <T> T parseObject(String text, TypeReference<T> type, String ...format) {
        ParserConfig config = ParserConfig.getGlobalInstance();
        if (format.length > 0) {
            config.putDeserializer(Date.class, new MyDateFormatDeserializer(format));
        }else{
            config.putDeserializer(Date.class, DateDeserializer.instance);
        }
        return (T) JSON.parseObject(text, type.getType(), config, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }
    
    @SuppressWarnings("unchecked")
    public static <T> T parseObject(String text, Type type, String ...format) {
        ParserConfig config = ParserConfig.getGlobalInstance();
        if (format.length > 0) {
            config.putDeserializer(Date.class, new MyDateFormatDeserializer(format));
        }else{
            config.putDeserializer(Date.class, DateDeserializer.instance);
        }
        return (T) JSON.parseObject(text, type, config, JSON.DEFAULT_PARSER_FEATURE, new Feature[0]);
    }
    
    public static String toJSONString(Object obj, String... format) {
        SerializerFeature[] features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames, SerializerFeature.WriteNonStringKeyAsString };
        if (format.length > 0) {
            return JSON.toJSONStringWithDateFormat(obj, format[0], features);
        }
        return JSON.toJSONString(obj, features);
    }
    
    public static String toPrettyString(Object obj) {
        SerializerFeature[] features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames, SerializerFeature.WriteNonStringKeyAsString, SerializerFeature.PrettyFormat };
        return JSON.toJSONStringWithDateFormat(obj, "yyyy-MM-dd HH:mm:ss", features);
    }
    
    public static String toJSONString(Object obj, SerializeFilter filter) {
        SerializerFeature[] features = new SerializerFeature[] { SerializerFeature.WriteMapNullValue,
                SerializerFeature.QuoteFieldNames, SerializerFeature.WriteNonStringKeyAsString };
        return JSON.toJSONString(obj, filter, features);
    }

    public static Map<String, Object> parseJSON2Map(String jsonStr) {
        Map<String, Object> map = JSON.parseObject(jsonStr, new TypeReference<Map<String, Object>>() {});
        if (null == map) {
            map = new HashMap<String, Object>();
        }
        return map;
    }

    public static List<Map<String, Object>> parseJSON2List(String jsonStr) {
        List<Map<String, Object>> list = JSON.parseObject(jsonStr, new TypeReference<List<Map<String, Object>>>() {});
        if (null == list) {
            list = new ArrayList<Map<String,Object>>();
        }
        return list;
    }
    
    public static <T> List<T> parseJSON2ListBean(String jsonStr) {
        List<T> list = JSON.parseObject(jsonStr, new TypeReference<List<T>>() {});
        if (null == list) {
            list = new ArrayList<T>();
        }
        return list;
    }
    
}
