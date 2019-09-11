package com.zkhy.officialWebsite.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.zkhy.officialWebsite.common.enums.ResponseCodeEnum;
import com.zkhy.officialWebsite.exception.ServiceException;

public class FileUtil {
	protected static Logger LOGGER = LoggerFactory.getLogger(FileUtil.class);

	/**
	 * 
	 * @param file 文件
	 * @param fileName 文件名
	 * @param filePath 存储路径
	 * @return
	 */
	public static Map<String, Object> upload(MultipartFile file, String fileName, String filePath) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(null == fileName || "".equals(fileName) || null == filePath || "".equals(filePath)) {
			return null;
		}

		try {
			File realFile = new File(filePath + fileName);
			FileUtils.writeByteArrayToFile(realFile, file.getBytes());
		} catch (IOException e) {
			e.printStackTrace();
		}

		resMap.put("resourcesLink", filePath.substring(filePath.indexOf("resources"), filePath.length())+fileName);
		return resMap;
	}

	/**
	 * Base64文件上传
	 * @param imgStr
	 * @param filePath
	 * @param fileName
	 * @return 图片路径
	 */
	public static String uploadBase64Img(String imgStr, String filePath, String fileName) {
		Map<String, Object> resMap = new HashMap<String, Object>();
		if(null == fileName || "".equals(fileName) || null == filePath || "".equals(filePath)
				|| null == imgStr || "".equals(imgStr)) {
			return null;
		}

		try {
			byte[] byteImg = Base64Util.decodeStr(imgStr);
			OutputStream out = new FileOutputStream(filePath + fileName);
			out.write(byteImg);
			out.flush();
			out.close();
			String link = filePath.substring(filePath.indexOf("resources"), filePath.length())+fileName;
			return link;
		} catch (FileNotFoundException e) {
			LOGGER.error("上传Base64文件错误, 文件不存在：{}", e);
			return null;
		} catch (IOException e) {
			LOGGER.error("上传Base64文件错误：{}", e);
			return null;
		}
	}

	/**
	 * 删除文件
	 * @param filename
	 * @throws IOException
	 */
	public static void deleteFile(String filename) {
		File file = new File(filename);
		if (file.isDirectory()) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "需删除的内容非文件");
		}
		if (!file.exists()) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "需删除的文件不存在");
		}
		if (!file.delete()) {
			throw new ServiceException(ResponseCodeEnum.ERROR.getCode(), "文件删除失败");
		}
		FileUtils.deleteQuietly(file);
	}
}
