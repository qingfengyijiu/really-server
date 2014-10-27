/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

/**
 * @since 0.1 2013-1-9,上午9:33:41下午
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public class LongDateJsonDeserializer extends JsonDeserializer<Date> {

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonDeserializer#deserialize(org.codehaus.jackson.JsonParser, org.codehaus.jackson.map.DeserializationContext)
	 */
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)	throws IOException, JsonProcessingException {
			String date = jp.getText();
		if(StringUtils.isNotBlank(date)) {
			DateFormat formatter = ApplicationContextUtils.getBean(DateFormatterFactory.class).createLongDateFormat(LocaleContextHolder.getLocale());  
			try {
				return formatter.parse(date);
			} catch (ParseException e) {
				e.printStackTrace();
				return null;
			}
		}
		return null;
	}

}
