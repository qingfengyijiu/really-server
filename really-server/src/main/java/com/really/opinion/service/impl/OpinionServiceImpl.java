/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:53:04
 * @description:
 */
package com.really.opinion.service.impl;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.really.comment.domain.Comment;
import com.really.comment.service.CommentService;
import com.really.news.domain.News;
import com.really.news.service.NewsService;
import com.really.opinion.dao.OpinionDao;
import com.really.opinion.domain.Opinion;
import com.really.opinion.service.OpinionService;
import com.really.opinion.util.TruthDegreeScoreCounter;
import com.really.user.domain.User;
import com.really.user.service.UserService;
import com.zhangjx.wx.persistence.GenericDao;
import com.zhangjx.wx.persistence.QLQueryTemplate;
import com.zhangjx.wx.persistence.QueryTemplate;
import com.zhangjx.wx.persistence.QueryUtils;
import com.zhangjx.wx.persistence.impl.GenericServiceImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:53:04
 *
 */
@Service("opinionService")
public class OpinionServiceImpl extends
		GenericServiceImpl<Opinion, Long> implements OpinionService {
	
	@Resource
	private UserService userService;
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private NewsService newsService;
	
	@Resource
	private OpinionDao dao;

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<Opinion, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.really.opinion.service.OpinionService#add(java.lang.String, long, com.really.opinion.domain.Opinion)
	 */
	@Override
	@Transactional
	public long add(long userId, long commentId, Opinion opinion) {
		// add opinion
		User user = this.userService.find(userId);
		Comment comment = this.commentService.find(commentId);
		opinion.setCreator(user);
		opinion.setComment(comment);
		opinion.setCreateTime(new Date());
		this.persist(opinion);
		// change news truth degree
		double userTruthDegreeScore = user.getTruth_degree_score();
		News news = comment.getNews();
		double oNewsTruthDegree = news.getTruthDegree();
		long newsOpinionTimes = news.getOpinionTimes();
		double nNewsTruthDegree = _countNewsTruthDegree(oNewsTruthDegree, newsOpinionTimes, opinion.getTruthDegree(), userTruthDegreeScore);
		news.setTruthDegree(nNewsTruthDegree);
		news.setOpinionTimes(newsOpinionTimes + 1);
		this.newsService.merge(news);
		// change comment attention
		comment.setAttention(comment.getAttention() + 1);
		this.commentService.merge(comment);
		return opinion.getId();
	}
	
	private double _countNewsTruthDegree(double old, long oldTimes, int opinionTruthDegree, double userTruthDegreeScore) {
		double newTruthDegree = 0D;
		long newTimes = oldTimes + 1;
		newTruthDegree = (old * oldTimes + TruthDegreeScoreCounter.getScore(opinionTruthDegree, userTruthDegreeScore)) / newTimes;
		return newTruthDegree;
	}

	/* (non-Javadoc)
	 * @see com.really.opinion.service.OpinionService#findPages(java.util.Map)
	 */
	@Override
	public List<Opinion> findPages(Map<String, Object> data) {
		int count = (Integer) data.get("pageCount");
		long commentId = (Long)data.get("commentId");
		QueryTemplate qt = new QLQueryTemplate("select o from Opinion o where o.comment.id = :commentId order by o.createTime desc");
		qt.addParameter("commentId", commentId);
		qt.setFirstResult((count - 1) * QueryUtils.PAGE_SIZE); 
		qt.setMaxResults(QueryUtils.PAGE_SIZE);
		return this.find(qt);
	}

	/* (non-Javadoc)
	 * @see com.really.opinion.service.OpinionService#getAttentionCount(long)
	 */
	@Override
	public long getAttentionCount(long commentId) {
		QueryTemplate qt = new QLQueryTemplate("select count(o) from Opinion o where o.comment.id = :commentId");
		qt.addParameter("commentId", commentId);
		return this.findCount(qt);
	}

}
