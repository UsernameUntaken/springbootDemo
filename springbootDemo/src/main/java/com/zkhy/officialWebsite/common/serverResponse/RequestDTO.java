package com.zkhy.officialWebsite.common.serverResponse;

import java.util.LinkedHashMap;
import java.util.List;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.exception.ServiceException;
import com.zkhy.officialWebsite.util.JsonUtil;

@SuppressWarnings("serial")
public class RequestDTO extends LinkedHashMap<String,Object>{
	
	public String getParam(String key) {
		if (!containsKey(key)) {
			return null;
		}
		try {
			return get(key).toString();
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误。期望字符型, 错误的参数为：" + key);
		}
	}
	
	public Integer getIntParam(String key) {
		if (!containsKey(key)) {
			return null;
		}
		try {
			return Integer.parseInt(get(key).toString());
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误。期望整型, 错误的参数为：" + key);
		}
	}
	
	public JsonArray getArrayParm(String key){
		if (!containsKey(key)) {
			return null;
		}
		try {
			return new JsonParser().parse(get(key).toString()).getAsJsonArray();
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误。期望数组对象, 错误的参数为：" + key);
		}
	}
	
	public <T> List<T> getListParam(String key, Class<T> clazz) {
		if (!containsKey(key)) {
			return null;
		}
		
		try {
			return JsonUtil.fromJsonToList(JsonUtil.toJson(get(key)), clazz);
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误。期望数组, 错误的参数为：" + key);
		}
	}
	
	public <T> T getParam(Class<T> clazz) {
		try {
			return JsonUtil.fromJsons(JsonUtil.toJson(this), clazz);
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误: " + clazz);
		}
	}
	
	public <T> T getObjectParam(String key, Class<T> clazz) {
		try {
			return JsonUtil.fromJsons(JsonUtil.toJson(get(key)), clazz);
		} catch (Exception e) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "参数错误, 期望对象:" + clazz);
		}
	}
}
