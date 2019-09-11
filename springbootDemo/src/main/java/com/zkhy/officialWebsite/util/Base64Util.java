package com.zkhy.officialWebsite.util;

import org.apache.commons.codec.binary.Base64;


public class Base64Util {
	
	/**
	 * 
	 * @param encodeStr
	 * @return
	 */
	public static byte[] decodeStr(String encodeStr){
		byte[] b = encodeStr.getBytes();
		Base64 base64=new Base64();
		return base64.decode(b);
	}

}
