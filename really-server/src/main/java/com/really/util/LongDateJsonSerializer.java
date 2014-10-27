/*
 * (c) Copyright 2012 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.io.IOException;
import java.text.DateFormat;
import java.util.Date;

import org.springframework.context.i18n.LocaleContextHolder;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

/**
 * @since 0.1 2013-1-6,下午2:48:31下午
 *
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  0.1 
 */
public class LongDateJsonSerializer extends JsonSerializer<Date> {

	/* (non-Javadoc)
	 * @see org.codehaus.jackson.map.JsonSerializer#serialize(java.lang.Object, org.codehaus.jackson.JsonGenerator, org.codehaus.jackson.map.SerializerProvider)
	 */
	@Override
	public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider) throws IOException,JsonProcessingException {
		DateFormat formatter = ApplicationContextUtils.getBean(DateFormatterFactory.class).createLongDateFormat(LocaleContextHolder.getLocale());
        String formattedDate = formatter.format(value);  
        jgen.writeString(formattedDate);  
	}

}
