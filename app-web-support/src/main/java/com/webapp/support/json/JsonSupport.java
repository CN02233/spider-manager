package com.webapp.support.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.gson.JsonArray;
import com.webapp.support.PageField;

import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by pc on 2017/7/5.
 */
public class JsonSupport {
    public static <T> String objectToJson(T object)
    {
        ObjectMapper mapper = new ObjectMapper();
        String jsonValue = null;
        try {
            jsonValue = mapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonValue;
    }

    public static HashMap jsonToMap(String jsonVal){
        ObjectMapper mapper = new ObjectMapper();
        try {
            HashMap mapVal = mapper.readValue(jsonVal, HashMap.class);
            return mapVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object jsonToObect(String jsonVal, Class clazz){

        List<PageField> tList = new ArrayList<>();
        tList.getClass();

        ObjectMapper mapper = new ObjectMapper();
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            Object list = mapper.readValue(jsonVal, t.constructCollectionType(ArrayList.class, PageField.class));

            Object mapVal = mapper.readValue(jsonVal,tList.getClass());
            return mapVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object jsonToList4Generic(String jsonVal, Class rootClazz,Class genericClazz){
        ObjectMapper mapper = new ObjectMapper();
        Object mapVal = null;
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            mapVal = mapper.readValue(jsonVal, t.constructCollectionType(rootClazz, genericClazz));
            return mapVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapVal;
    }

    public static Object jsonToMap4Generic(String jsonStr, Class<?> rootClassType, Class<?>[] jsonObjType) {
        ObjectMapper mapper = new ObjectMapper();
        Object mapVal = null;
        try {
            TypeFactory t = TypeFactory.defaultInstance();
            mapVal = mapper.readValue(jsonStr, t.constructMapLikeType(rootClassType, jsonObjType[0],jsonObjType[1]));
            return mapVal;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return mapVal;
    }

//    public static Object jsonToObect(String jsonVal, Type type){
//
//        ObjectMapper mapper = new ObjectMapper();
//
//        mapper.getTypeFactory().constructCollectionLikeType(ArrayList.class,type);
//
//        try {
//            Object mapVal = mapper.readValue(jsonVal, clazz);
//            return mapVal;
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return null;
//    }

}
