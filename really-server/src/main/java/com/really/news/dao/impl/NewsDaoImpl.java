/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:41:54
 * @description:
 */
package com.really.news.dao.impl;

import org.springframework.stereotype.Repository;

import com.really.news.dao.NewsDao;
import com.really.news.domain.News;
import com.zhangjx.wx.persistence.impl.GenericDaoImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:41:54
 *
 */
@Repository("newsDao")
public class NewsDaoImpl extends GenericDaoImpl<News, Long> implements NewsDao {

	/**
	 * @param persistentClass
	 */
	public NewsDaoImpl() {
		super(News.class);
	}

}
