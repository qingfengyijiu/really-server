/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:44:12
 * @description:
 */
package com.really.news.service.impl;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.really.news.dao.NewsDao;
import com.really.news.domain.News;
import com.really.news.service.NewsService;
import com.really.user.domain.User;
import com.really.user.service.UserService;
import com.zhangjx.wx.persistence.GenericDao;
import com.zhangjx.wx.persistence.impl.GenericServiceImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:44:12
 *
 */
@Service("newsService")
public class NewsServiceImpl extends GenericServiceImpl<News, Long> implements NewsService {

	@Resource
	private UserService userService;
	
	@Resource
	private NewsDao dao;
	
	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<News, Long> getDao() {
		return dao;
	}


	/* (non-Javadoc)
	 * @see com.really.news.service.NewsService#add(java.lang.String, com.really.news.domain.News)
	 */
	@Override
	public long add(long userId, News news) {
		User user = userService.find(userId);
		news.setCreator(user);
		Date createTime = new Date();
		news.setCreateTime(createTime);
		this.persist(news);
		return news.getId();
	}

}
