/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.io.IOException;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

 /**
 * @since 1.0 2013-3-18,下午8:05:33
 *
 * @author  <a href="mailto:gaobg@legendsec.com">高保国</a>
 * @version  1.0 
 */
public class RequestMethodJsonDeserializer extends JsonDeserializer<RequestMethod> {

    /**
     * <p>Title: deserialize</p>
     * <p>Description: TODO</p>
     * @param arg0
     * @param arg1
     * @return
     * @throws IOException
     * @throws JsonProcessingException
     * @see com.fasterxml.jackson.databind.JsonDeserializer#deserialize(com.fasterxml.jackson.core.JsonParser, com.fasterxml.jackson.databind.DeserializationContext)
     * <p>{@inheritDoc}</p>
     */
    
    @Override
    public RequestMethod deserialize(JsonParser jp, DeserializationContext arg1) throws IOException, JsonProcessingException {
        String method = jp.getText();
        if(StringUtils.isNotBlank(method)) {
        	for(RequestMethod m : RequestMethod.values()) {
        		if(m.name().equals(method)) {
        			return m;
        		}
        	}
        }
    	return RequestMethod.GET;
    }
    
}