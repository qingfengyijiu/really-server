/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:47:50
 * @description:
 */
package com.really.comment.service;

import java.util.List;
import java.util.Map;

import com.really.comment.domain.Comment;
import com.really.news.domain.News;
import com.zhangjx.wx.persistence.GenericService;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:47:50
 *
 */
public interface CommentService extends GenericService<Comment, Long> {

	long add(long userId, long newsId, Comment comment);
	
	long addWithNews(long userId, News news, Comment comment);
	
	List<Comment> findPages(Map<String, Object> data);
	
}
