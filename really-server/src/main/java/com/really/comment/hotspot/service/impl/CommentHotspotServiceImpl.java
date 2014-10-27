/**
 * @author zhangjx
 * @time 2014年9月3日 上午5:50:56
 * @description:
 */
package com.really.comment.hotspot.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.really.comment.hotspot.dao.CommentHotspotDao;
import com.really.comment.hotspot.domain.CommentHotspot;
import com.really.comment.hotspot.service.CommentHotspotService;
import com.zhangjx.wx.persistence.GenericDao;
import com.zhangjx.wx.persistence.QLQueryTemplate;
import com.zhangjx.wx.persistence.QueryTemplate;
import com.zhangjx.wx.persistence.impl.GenericServiceImpl;

/**
 * @author zhangjx
 * @date 2014年9月3日 上午5:50:56
 *
 */
@Service("commentHotspotService")
public class CommentHotspotServiceImpl extends
		GenericServiceImpl<CommentHotspot, Long> implements
		CommentHotspotService {
	
	@Resource
	private CommentHotspotDao dao;
	
	@Override
	public GenericDao<CommentHotspot, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.really.comment.hotspot.service.CommentHotspotService#findHotspots(java.util.Date, int)
	 */
	@Override
	public List<List<CommentHotspot>> findHotspots(Date startDate, int days) {
		List<List<CommentHotspot>> result = new ArrayList<List<CommentHotspot>>();
		List<Date> dates = this._getQueryDaysBefore(startDate, days);
		for(Date day : dates) {
			List<CommentHotspot> commentHotspots = this.getHotspotsByDay(day);
			if(commentHotspots != null && commentHotspots.size() == 5) {
				result.add(commentHotspots);
			}
		}
		return result;
	}

	/* (non-Javadoc)
	 * @see com.really.comment.hotspot.service.CommentHotspotService#getHotspotsByDay(java.util.Date)
	 */
	@Override
	public List<CommentHotspot> getHotspotsByDay(Date day) {
		day = this._processDatetime2Date(day);
		QueryTemplate qt = new QLQueryTemplate("select h from CommentHotspot h where h.rankDay = :rankDay order by h.hotspotValue desc");
		qt.addParameter("rankDay", day);
		qt.setMaxResults(5);
		return this.find(qt);
	}
	
	private Date _processDatetime2Date(Date datetime) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(datetime);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		return cal.getTime();
	}
	
	private List<Date> _getQueryDaysBefore(Date startDate, int days) {
		List<Date> dates = new ArrayList<Date>();
		Calendar dayCalendar = new GregorianCalendar();
		dayCalendar.setTime(startDate);
		dayCalendar.set(Calendar.HOUR_OF_DAY, 0);
		dayCalendar.set(Calendar.MINUTE, 0);
		dayCalendar.set(Calendar.SECOND, 0);
		dayCalendar.set(Calendar.MILLISECOND, 0);
		dates.add(dayCalendar.getTime());
		for(int i = 1; i < days; i++) {
			dayCalendar.add(Calendar.DATE, -1);
			dates.add(dayCalendar.getTime());
		}
		return dates;
	}

}
