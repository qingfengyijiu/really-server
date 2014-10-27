/**
 * @author zhangjx
 * @time 2014年7月14日 上午2:48:34
 * @description:
 */
package com.really.opinion.controller;

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

import com.really.opinion.domain.Opinion;
import com.really.opinion.service.OpinionService;

/**
 * @author zhangjx
 * @date 2014年7月14日 上午2:48:34
 *
 */
@Controller
@RequestMapping(value="/opinion")
public class OpinionController {
	
	@Resource
	private OpinionService opinionService;
	
	@ResponseBody
	@RequestMapping(value="/add", method={RequestMethod.POST}, consumes={"application/json"}, produces={"application/json"})
	public long add(@RequestBody Map<String, Object> data) {
		long commentId = new Long(data.get("commentId").toString());
		int truthDegree = (Integer)data.get("truthDegree");
		int relationship;
		try {
			relationship = (Integer) data.get("relationship");
		} catch (Exception e) {
			relationship = 0;
		}
		String content = (String)data.get("content");
		long userId = Long.parseLong(data.get("userId").toString());
		Opinion opinion = new Opinion();
		opinion.setContent(content);
		opinion.setTruthDegree(truthDegree);
		opinion.setRelationship(relationship);
		return this.opinionService.add(userId, commentId, opinion);
	}
	
	@ResponseBody
	@RequestMapping(value="/list/{commentId}/{pageCount}", method={RequestMethod.GET}, produces={"application/json"})
	public List<Opinion> list(@PathVariable("commentId") long commentId, @PathVariable("pageCount") int pageCount) {
		Map<String, Object> queryData = new HashMap<String, Object>();
		queryData.put("pageCount", pageCount);
		queryData.put("commentId", commentId);
		return this.opinionService.findPages(queryData);
	}
}
