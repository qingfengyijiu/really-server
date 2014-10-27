/**
 * @author zhangjx
 * @time 2014年9月3日 上午5:49:55
 * @description:
 */
package com.really.comment.hotspot.service;

import java.util.Date;
import java.util.List;

import com.really.comment.hotspot.domain.CommentHotspot;
import com.zhangjx.wx.persistence.GenericService;

/**
 * @author zhangjx
 * @date 2014年9月3日 上午5:49:55
 *
 */
public interface CommentHotspotService extends
		GenericService<CommentHotspot, Long> {

	List<List<CommentHotspot>> findHotspots(Date startDate, int days);
	
	List<CommentHotspot> getHotspotsByDay(Date day);
	
}
