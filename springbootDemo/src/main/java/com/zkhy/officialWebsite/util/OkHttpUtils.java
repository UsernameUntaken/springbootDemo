package com.zkhy.officialWebsite.util;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * okhttp工具类
 *
 */
public class OkHttpUtils {
	private static Logger LOGGER = LoggerFactory.getLogger(OkHttpUtils.class);
	
	private static final OkHttpClient HTTP_AGENT = new OkHttpClient();
	
	public static String get(String url, Map<String, Object> urlParams) {
		url += buildUrlParamsStr(urlParams);
		Request request = new Request.Builder()
				.url(url)
				.get()
				.build();
		
		return execute(url, request);
	}

	public static String postFromData(String url, Map<String, Object> fromData) {
		okhttp3.FormBody.Builder fromBodyBuilder = new FormBody.Builder();
		fromData.forEach((k, v) -> {
			fromBodyBuilder.add(k, v.toString());
		});
//		for (Map.Entry<String, Object> entry: fromData.entrySet()) {
//			fromBodyBuilder.add(entry.getKey(), entry.getValue().toString());
//		}
		FormBody formBody = fromBodyBuilder.build();
		
		Request request = new Request.Builder()
                .url(url)
                .post(formBody)
                .build();
		
		return execute(url, request);
	}
	
	private static String execute(String url, Request request) {
		String method = request.method().toUpperCase();
		LOGGER.info("[okhttp request] {} {}", method, url);
		
		try {
			Response response = HTTP_AGENT.newCall(request).execute();
			int code = response.code();
			String res = response.body().string();
			
			LOGGER.info("[okhttp response] {} {} data: {}", method, url, res);
			if(code >= 200 && code <= 202) {
				return res;
			} else {
				String msg = String.format("请求报错。 %s data: %s", code, res);
				LOGGER.error(msg);
				throw new RuntimeException(msg);
			}
		} catch (IOException e) {
			String msg = String.format("请求报错。 %s %s", method, url);
			LOGGER.error(msg, e);
			throw new RuntimeException(msg);
		}
	}
	
	private static String buildUrlParamsStr(Map<String, Object> urlParams) {
		if (null != urlParams && urlParams.size() > 0) {
			StringBuffer paramStr = new StringBuffer("?");
			urlParams.forEach((k, v) -> {
				paramStr.append(k);
				paramStr.append("=");
				paramStr.append(v);
				paramStr.append("&");
			});
			return paramStr.toString();
		}
		return "";
	}
}
