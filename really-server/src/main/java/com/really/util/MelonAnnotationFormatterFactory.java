/*
 * (c) Copyright 2013 网神信息技术（北京）股份有限公司
 * http://www.legendsec.com
 */
package com.really.util;

import java.lang.annotation.Annotation;

import org.springframework.format.AnnotationFormatterFactory;

/**
 * @since 1.0 2013-4-11,上午10:12:08
 * 
 * @author  <a href="mailto:ganhuan@legendsec.com">甘焕</a>
 * @version  1.0 
 */
public interface MelonAnnotationFormatterFactory<A extends Annotation> extends AnnotationFormatterFactory<A> {
    
}
