/**
 * @author zhangjx
 * @time 2014年9月3日 上午6:09:17
 * @description:
 */
package com.really.comment.hotspot.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.really.comment.hotspot.domain.CommentHotspot;
import com.really.comment.hotspot.service.CommentHotspotService;
import com.really.enumeration.OperationStatus;

/**
 * @author zhangjx
 * @date 2014年9月3日 上午6:09:17
 *
 */
@Controller
@RequestMapping(value="/comment/hotspot")
public class CommentHotspotController {
	
	@Resource
	CommentHotspotService commentHotspotService;
	
	@ResponseBody
	@RequestMapping(value="/query/{startDate}/{days}")
	public Map<String, Object> getHotspots(@PathVariable("startDate") long startDate, @PathVariable("days") int days) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			List<List<CommentHotspot>> hotspots = this.commentHotspotService.findHotspots(new Date(startDate), days);
			result.put("status", "0");
			result.put("hotspots", hotspots);
		} catch (Exception e) {
			result.put("status", "1");
		}
		return result;
	}
}
