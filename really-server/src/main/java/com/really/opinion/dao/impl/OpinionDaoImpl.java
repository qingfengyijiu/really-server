/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:51:24
 * @description:
 */
package com.really.opinion.dao.impl;

import org.springframework.stereotype.Repository;

import com.really.opinion.dao.OpinionDao;
import com.really.opinion.domain.Opinion;
import com.zhangjx.wx.persistence.impl.GenericDaoImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:51:24
 *
 */
@Repository("opinionDao")
public class OpinionDaoImpl extends GenericDaoImpl<Opinion, Long> implements OpinionDao {

	/**
	 * @param persistentClass
	 */
	public OpinionDaoImpl() {
		super(Opinion.class);
	}

}
