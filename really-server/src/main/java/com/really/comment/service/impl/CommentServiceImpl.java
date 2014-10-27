/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:48:29
 * @description:
 */
package com.really.comment.service.impl;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.really.comment.dao.CommentDao;
import com.really.comment.domain.Comment;
import com.really.comment.service.CommentService;
import com.really.news.domain.News;
import com.really.news.service.NewsService;
import com.really.user.domain.User;
import com.really.user.service.UserService;
import com.zhangjx.wx.persistence.GenericDao;
import com.zhangjx.wx.persistence.QLQueryTemplate;
import com.zhangjx.wx.persistence.QueryTemplate;
import com.zhangjx.wx.persistence.QueryUtils;
import com.zhangjx.wx.persistence.impl.GenericServiceImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:48:29
 *
 */
@Service("commentService")
public class CommentServiceImpl extends GenericServiceImpl<Comment, Long> implements CommentService {

	@Resource
	private UserService userService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private CommentDao dao;
	
	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Comment, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.really.comment.service.CommentService#add(java.lang.String, long, com.really.comment.domain.Comment)
	 */
	@Override
	public long add(long userId, long newsId, Comment comment) {
		User user = userService.find(userId);
		News news = newsService.find(newsId);
		comment.setCreator(user);
		comment.setNews(news);
		this.persist(comment);
		return comment.getId();
	}

	/* (non-Javadoc)
	 * @see com.really.comment.service.CommentService#findPages()
	 */
	@Override
	public List<Comment> findPages(Map<String, Object> data) {
		int count = (Integer) data.get("pageCount");
		int queryZone = (Integer)data.get("queryZone");
		StringBuilder sb = new StringBuilder();
		sb.append("select c from Comment c where 1 =1");
		boolean conditional = queryZone == 1 || queryZone == 2 || queryZone == 3;
		Calendar calendar = new GregorianCalendar();
		switch(queryZone) {
			case 1 :
				calendar.add(Calendar.DATE, -1);
				break;
			case 2 :
				calendar.add(Calendar.DATE, -3);
				break;
			case 3 :
				calendar.add(Calendar.DATE, -7);
				break;
			default :
				break;
		}
		if(conditional) {
			sb.append(" and c.createTime > :createTime");
		}
		sb.append(" order by c.createTime desc");
		QueryTemplate qt = new QLQueryTemplate(sb.toString());
		qt.setFirstResult((count - 1) * QueryUtils.PAGE_SIZE);
		qt.setMaxResults(QueryUtils.PAGE_SIZE);
		if(conditional) {
			qt.addParameter("createTime", calendar.getTime());
		}
		return this.find(qt);
	}

	/* (non-Javadoc)
	 * @see com.really.comment.service.CommentService#addWithNews(java.lang.String, com.really.news.domain.News, com.really.comment.domain.Comment)
	 */
	@Override
	public long addWithNews(long userId, News news, Comment comment) {
		Date createTime = new Date();
		User user = this.userService.find(userId);
		news.setCreator(user);
		news.setCreateTime(createTime);
		this.newsService.persist(news);
		comment.setCreator(user);
		comment.setCreateTime(createTime);
		comment.setNews(news);
		this.persist(comment);
		return comment.getId();
	}

}
