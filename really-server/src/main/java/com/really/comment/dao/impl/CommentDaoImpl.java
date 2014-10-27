/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:46:30
 * @description:
 */
package com.really.comment.dao.impl;

import org.springframework.stereotype.Repository;

import com.really.comment.dao.CommentDao;
import com.really.comment.domain.Comment;
import com.zhangjx.wx.persistence.impl.GenericDaoImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:46:30
 *
 */
@Repository("commentDao")
public class CommentDaoImpl extends GenericDaoImpl<Comment, Long> implements CommentDao {

	/**
	 * @param persistentClass
	 */
	public CommentDaoImpl() {
		super(Comment.class);
	}

}
