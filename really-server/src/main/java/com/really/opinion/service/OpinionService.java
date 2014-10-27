/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:52:26
 * @description:
 */
package com.really.opinion.service;

import java.util.List;
import java.util.Map;

import com.really.opinion.domain.Opinion;
import com.zhangjx.wx.persistence.GenericService;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:52:26
 *
 */
public interface OpinionService extends GenericService<Opinion, Long> {

	long add(long userId, long commentId, Opinion opinion);
	
	List<Opinion> findPages(Map<String, Object> data);
	
	long getAttentionCount(long commentId);
}
