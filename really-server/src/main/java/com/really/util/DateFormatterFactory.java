/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.annotation.Resource;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

/**
 * @since 1.0 2013-4-2,下午4:30:59
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component("dateFormatterFactory")
public class DateFormatterFactory {
    
	@Resource
    private MessageSource messageSource;
    
    /**
     * 
     * @return
     */
    public DateFormat createShortDateFormat(Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(messageSource.getMessage("date.short", null, locale));  
        return formatter;
    }
    
    public DateFormat createLongDateFormat(Locale locale) {
        SimpleDateFormat formatter = new SimpleDateFormat(messageSource.getMessage("date.long", null, locale));  
        return formatter;
    }
    
}
