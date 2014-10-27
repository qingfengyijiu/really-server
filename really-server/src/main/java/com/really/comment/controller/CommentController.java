/**
 * @author zhangjx
 * @time 2014年7月14日 上午1:16:49
 * @description:
 */
package com.really.comment.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.really.comment.domain.Comment;
import com.really.comment.service.CommentService;
import com.really.enumeration.OperationStatus;
import com.really.news.domain.News;
import com.really.opinion.service.OpinionService;

/**
 * @author zhangjx
 * @date 2014年7月14日 上午1:16:49
 *
 */

@Controller
@RequestMapping(value="/comment")
public class CommentController {
	
	@Resource
	private CommentService commentService;
	
	@Resource
	private OpinionService opinionService;

	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/add", method={RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
	public long add(@RequestBody Map data) {
		Long newsId = new Long((Integer)data.get("news_id"));
		long userId = Long.parseLong(data.get("userId").toString());
		String commentContent = (String) data.get("comment_content");
		Date createTime = new Date();
		Comment comment = new Comment();
		comment.setContent(commentContent);
		comment.setCreateTime(createTime);
		return this.commentService.add(userId, newsId, comment);
	}
	
	@ResponseBody
	@RequestMapping(value="/list/{queryZone}/{pageCount}", method={RequestMethod.GET}, produces={"application/json"})
	public List<Map<String, Object>> list(@PathVariable("queryZone") int queryZone, @PathVariable("pageCount") int pageCount) {
		List<Map<String, Object>> results = new ArrayList<Map<String, Object>>();
		Map<String, Object> queryData = new HashMap<String, Object>();
		queryData.put("queryZone", queryZone);
		queryData.put("pageCount", pageCount);
		List<Comment> comments =  this.commentService.findPages(queryData);
		for(int i = 0; i < comments.size(); i++) {
			Comment comment = comments.get(i);
			Map<String, Object> result = new HashMap<String, Object>();
			long commentId = comment.getId();
			result.put("comment_id", commentId);
			result.put("comment_content", comment.getContent());
			result.put("comment_attention", this.opinionService.getAttentionCount(commentId));
			result.put("background", comment.getBackground());
			result.put("alpha", comment.getAlpha());
			result.put("news_url", comment.getNews().getUrl());
			result.put("news_title", comment.getNews().getTitle());
			result.put("news_truth_degree", comment.getNews().getTruthDegree());
			results.add(result);
		}
		return results;
	}
	
	@ResponseBody
	@RequestMapping(value="/add/news", method={RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
	public Map<String, Object> addWithNews(@RequestBody Map<String, Object> data) {
		Map<String, Object> responseData = new HashMap<String, Object>();
		try {
			long userId = Long.parseLong(data.get("userId").toString());
			String news_url = (String) data.get("news_url");
			String news_title = (String) data.get("news_title");
			String news_comment = (String) data.get("news_comment");
			int background = Integer.parseInt(data.get("background").toString());
			int alpha = Integer.parseInt(data.get("alpha").toString());
			Comment comment = new Comment();
			comment.setContent(news_comment);
			comment.setBackground(background);
			comment.setAlpha(alpha);
			News news = new News();
			news.setUrl(news_url);
			news.setTitle(news_title);
			this.commentService.addWithNews(userId, news, comment);
			responseData.put("status", "0");
		} catch (Exception e) {
			responseData.put("status", "1");
			responseData.put("error_code", "998");
		}
		return responseData;
		
	}
	
	@ResponseBody
	@RequestMapping(value="/{id}")
	public Map<String, Object> query(@PathVariable("id") long id) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			Comment comment = this.commentService.find(id);
			result.put("status", "0");
			result.put("comment_id", comment.getId());
			result.put("comment_content", comment.getContent());
			result.put("comment_attention", this.opinionService.getAttentionCount(id));
			result.put("background", comment.getBackground());
			result.put("alpha", comment.getAlpha());
			result.put("news_url", comment.getNews().getUrl());
			result.put("news_title", comment.getNews().getTitle());
			result.put("news_truth_degree", comment.getNews().getTruthDegree());
		} catch (Exception e) {
			result.put("status", "1");
			result.put("error_code", "998");
		}
		return result;
	}
}
