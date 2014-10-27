/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:25:20
 * @description:
 */
package com.really.news.controller;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.really.news.domain.News;
import com.really.news.service.NewsService;
import com.really.util.NewsParser;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:25:20
 *
 */
@Controller
@RequestMapping(value="/news")
public class NewsController {
	
	@Resource
	NewsService newsService;

	@ResponseBody
	@RequestMapping(value="/add", method={RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
	public long add(@RequestBody Map<String, Object> data) {
		long userId = Long.parseLong(data.get("userId").toString());
		String url = (String) data.get("news_url");
		String title = (String) data.get("news_title");
		String content = (String) data.get("news_content");
		News news = new News();
		news.setTitle(title);
		news.setUrl(url);
		news.setContent(content);
		return this.newsService.add(userId, news);
	}
	
	@SuppressWarnings("rawtypes")
	@ResponseBody
	@RequestMapping(value="/parselink", method={RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
	public Map parseLink(@RequestBody Map data) {
		String url = ((String) data.get("news_url")).trim();
		return NewsParser.parse(url);
	}
	
}
