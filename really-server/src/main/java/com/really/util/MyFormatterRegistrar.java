/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

/**
 * @since 1.0 2013-4-11,上午10:09:32
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
@Component
public class MyFormatterRegistrar implements BeanPostProcessor, FormatterRegistrar {
    
    private FormatterRegistry registry;
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessAfterInitialization(java.lang.Object, java.lang.String)
     */
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if(bean instanceof MelonAnnotationFormatterFactory) {
            if(registry != null) {
                registry.addFormatterForFieldAnnotation((MelonAnnotationFormatterFactory<?>)bean);
            }
        }
        return bean;
    }
    
    /* (non-Javadoc)
     * @see org.springframework.beans.factory.config.BeanPostProcessor#postProcessBeforeInitialization(java.lang.Object, java.lang.String)
     */
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        return bean;
    }

    /* (non-Javadoc)
     * @see org.springframework.format.FormatterRegistrar#registerFormatters(org.springframework.format.FormatterRegistry)
     */
    public void registerFormatters(FormatterRegistry registry) {
        this.registry = registry;
    }
    
}
