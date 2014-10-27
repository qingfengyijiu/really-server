/**
 * @author zhangjx
 * @time 2014年6月10日 下午7:21:10
 * @description:
 */
package com.really.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * @author zhangjx
 * @date 2014年6月10日 下午7:21:10
 *
 */
public class JsonHelper {
	
	private final static Logger logger = LoggerFactory.getLogger(JsonHelper.class);

	private JsonHelper() {
		
	}
	
	public static String parse(Object object, ObjectMapper objectMapper) {
		String json = "";
		try {
			json = objectMapper.writeValueAsString(object);
		} catch (Exception e) {
			logger.error("转换数据[" + object.toString() + "]时发生错误");
		}
		return json;
	}
}
