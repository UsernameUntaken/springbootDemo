package com.zkhy.officialWebsite.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;

public class SerialUtil {

	
	/**
	 * 随机生成不重复的32位id
	 * @return
	 */
	public static String getUid() {
		return UUID.randomUUID().toString().trim().replaceAll("-", "");
	}
	
	/**
	 * 编号生成方式：yyyyMMdd+时间戳后六位+四位随机数
	 * @return
	 */
	public static String createNum(){
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		StringBuffer sbf = new StringBuffer();
		sbf.append(sdf.format(date));
		String timeStr = String.valueOf(date.getTime());
		sbf.append(timeStr.subSequence(timeStr.length()-6, timeStr.length()));
		sbf.append(getRandNum(4));
		return sbf.toString();
	}
	
	/**
	 * 编号生成方式：SP+yyyyMMdd+时间戳后六位+四位随机数
	 * @return
	 */
	public static String createSPNum(){
		DateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Date date = new Date();
		StringBuffer sbf = new StringBuffer();
		sbf.append("SP");
		sbf.append(sdf.format(date));
		String timeStr = String.valueOf(date.getTime());
		sbf.append(timeStr.subSequence(timeStr.length()-6, timeStr.length()));
		sbf.append(getRandNum(4));
		return sbf.toString();
	}

	private static String getRandNum(int length){
		Random ram = new Random();
		StringBuffer sbf = new StringBuffer();
		for(int i = 0; i < length; i++){
			sbf.append(ram.nextInt(10));
		}
		if(sbf.length()>0){
			return sbf.toString();
		}
		return null;
	}
}
