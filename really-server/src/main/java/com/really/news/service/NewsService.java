/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:43:36
 * @description:
 */
package com.really.news.service;

import com.really.news.domain.News;
import com.zhangjx.wx.persistence.GenericService;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:43:36
 *
 */
public interface NewsService extends GenericService<News, Long> {

	long add(long userId, News news);
}
