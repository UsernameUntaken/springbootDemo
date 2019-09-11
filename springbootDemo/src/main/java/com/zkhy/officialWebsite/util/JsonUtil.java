package com.zkhy.officialWebsite.util;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.gson.Gson;

import net.sf.json.JSONObject;

public class JsonUtil {
private static Gson gson = new Gson();
	
	/**
	 * 对象转换成json
	 * @param object
	 * @return
	 */
	public static String toJson(Object object) {
		return gson.toJson(object);
	}
	
	
	/**
	 * json转换成对象
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> T fromJson(String json, Class<T> clazz) {
		if (json == null || StringUtils.isBlank(json)) {
            return null;
        }
		
		return gson.fromJson(json, clazz);
	}
	
	
	public static <T> List<T> fromJsonToList(String json, Class<T> clazz) {
        if (json == null || json.trim().length() == 0) {
            return null;
        }

        return JSONArray.parseArray(json, clazz);
    }
	
	/**
	 * 判断传入的字符串是否为完整正确的json格式
	 * @param jsonStr
	 * @return
	 */
	public static  boolean isJson(String jsonStr){
		if(null==jsonStr||StringUtils.isBlank(jsonStr)) {
			return false;  
		}
		try {
			fromJson(jsonStr, JSONObject.class);
			return  true;   
		} catch (Exception e) { 
			return false;  
		}
	}
	
	/**
	 * 判断传入的对象是否是JSONArray
	 * @param obj
	 * @return
	 */
	public static boolean isJSONArray(Object obj) {
		if(null==obj) {
			return false;
		}
		try {
			net.sf.json.JSONArray jarr = net.sf.json.JSONArray.fromObject(obj);
		}catch (Exception e) {
			return false;
		}
		return true;
	}
	
	public static <T> T fromJsons(String json, Class<T> clazz) {
        if (json == null || StringUtils.isBlank(json)) {
            return null;
        }

        return JSON.parseObject(json, clazz);
    }
	
	public static Map<String, Object> objectToMap(Object obj) throws Exception {    
        if(obj == null){    
            return null;    
        }   
  
        Map<String, Object> map = new HashMap<String, Object>();    
  
        Field[] declaredFields = obj.getClass().getDeclaredFields();    
        for (Field field : declaredFields) {    
            field.setAccessible(true);  
            map.put(field.getName(), field.get(obj));  
        }    
  
        return map;  
    } 
}
