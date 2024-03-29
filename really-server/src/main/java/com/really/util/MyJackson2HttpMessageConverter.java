package com.really.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.zhangjx.wx.persistence.PageQuery;


public class MyJackson2HttpMessageConverter extends	MappingJackson2HttpMessageConverter {

	private final static Logger logger = LoggerFactory.getLogger(MyJackson2HttpMessageConverter.class);
	
	private Map<String, String> configs = new HashMap<String, String>();
	
	/* (non-Javadoc)
	 * @see org.springframework.http.converter.json.MappingJackson2HttpMessageConverter#read(java.lang.reflect.Type, java.lang.Class, org.springframework.http.HttpInputMessage)
	 */
	@Override
	public Object read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException,
			HttpMessageNotReadableException {
		if(type instanceof Class) {
			Class<?> clazz = (Class<?>)type;
			if(clazz.getSuperclass()== PageQuery.class) {
				return readInternal(clazz, inputMessage);
			}
		}
		return super.read(type, contextClass, inputMessage);
	}

	/* (non-Javadoc)
	 * @see org.springframework.http.converter.AbstractHttpMessageConverter#readInternal(java.lang.Class, org.springframework.http.HttpInputMessage)
	 */
	@Override
	protected Object readInternal(Class<? extends Object> clazz,	HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
		JavaType javaType = getJavaType(clazz, null);
		if(clazz.getSuperclass() == PageQuery.class) {
			if (inputMessage instanceof ServletServerHttpRequest) {
				HttpServletRequest request = ((ServletServerHttpRequest)inputMessage).getServletRequest();
				ObjectNode node = null;
				if ("GET".equals(request.getMethod().toUpperCase())) {
					node = this.parseGetJson(request);
				} else {
					InputStream body = inputMessage.getBody();
					node = this.parsePostJson(body);
				}
				return getObjectMapper().reader(javaType).readValue(node);
			}
		}
		//
		return super.readInternal(clazz, inputMessage);
	}
	
	/**
	 * @param body
	 * @return
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	private ObjectNode parsePostJson(InputStream body) throws JsonParseException, JsonMappingException, IOException {
		ObjectNode objectNode = getObjectMapper().createObjectNode();
		StringBuilder builder = new StringBuilder();
		Scanner scanner = new Scanner(body);
		while(scanner.hasNext()) {
			builder.append(scanner.nextLine());
		}
		String jsonStr = builder.toString();
		if (logger.isDebugEnabled()) {
			logger.debug("QueryPage Json : {}", jsonStr);
		}
		//
		String[] entrys = jsonStr.split("&");
		for(String entry : entrys) {
			String[] map = entry.split("=");
			String key = map[0];
			if(map.length == 2 && configs.containsKey(key)) {
				String value = map[1];
				if("searchForm".equals(key)) {
					value = URLDecoder.decode(URLDecoder.decode(value, "UTF-8"), "UTF-8");
					objectNode.put(key,getObjectMapper().readValue(value,ObjectNode.class));
					continue;
				}
				objectNode.put(key, value);
			}
		}
		return objectNode;
	}

	/**
	 * @param request
	 * @return
	 * @throws IOException 
	 * @throws UnsupportedEncodingException 
	 * @throws JsonMappingException 
	 * @throws JsonParseException 
	 */
	private ObjectNode parseGetJson(HttpServletRequest request) throws JsonParseException, JsonMappingException, UnsupportedEncodingException, IOException {
		if (logger.isDebugEnabled()) {
			logger.debug("Pagination Json : {}", request.getQueryString());
		}
		ObjectNode objectNode = getObjectMapper().createObjectNode();
		for (String key : configs.keySet()) {
			String value = configs.get(key);
			if (StringUtils.isNotBlank(request.getParameter(key))) {
				value = request.getParameter(key);
				if("searchForm".equals(key)) {
					objectNode.put(key,getObjectMapper().readValue(URLDecoder.decode(value, "UTF-8"),ObjectNode.class));
					continue;
				}
			}
			objectNode.put(key, value);
		}
		return objectNode;
	}

	/**
	 * @param configs the configs to set
	 */
	public void setConfigs(Map<String, String> configs) {
		this.configs = configs;
	}
}
