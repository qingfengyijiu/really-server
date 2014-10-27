/**
 * @author zhangjx
 * @time 2014年9月3日 上午5:47:20
 * @description:
 */
package com.really.comment.hotspot.dao.impl;

import org.springframework.stereotype.Repository;

import com.really.comment.hotspot.dao.CommentHotspotDao;
import com.really.comment.hotspot.domain.CommentHotspot;
import com.zhangjx.wx.persistence.impl.GenericDaoImpl;

/**
 * @author zhangjx
 * @date 2014年9月3日 上午5:47:20
 *
 */
@Repository("commentHotspotDao")
public class CommentHotspotDaoImpl extends GenericDaoImpl<CommentHotspot, Long>
		implements CommentHotspotDao {

	/**
	 * @param persistentClass
	 */
	public CommentHotspotDaoImpl() {
		super(CommentHotspot.class);
	}

}
