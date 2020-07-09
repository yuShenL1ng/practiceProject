package com.practice.demo.util;

import java.util.UUID;

/**
 * 生成uuid
 * @author 崔俸恺
 * @version 创建时间：2020年2月14日
 */
public class GenerateUUID {
	
	public static String getUUID(){ 
		String uuid = UUID.randomUUID().toString(); 
		//去掉“-”符号 
		return uuid.replaceAll("-", "");
		}

}
