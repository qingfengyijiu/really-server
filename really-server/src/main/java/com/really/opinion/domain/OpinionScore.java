/**
 * @author zhangjx
 * @time 2014年8月31日 上午9:00:51
 * @description:
 */
package com.really.opinion.domain;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.zhangjx.wx.persistence.BaseDomain;

/**
 * @author zhangjx
 * @date 2014年8月31日 上午9:00:51
 *
 */
public class OpinionScore extends BaseDomain<Long> {
	
	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	private int truthDegree;

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return this.id;
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.BaseDomain#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return false;
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.BaseDomain#hashCode()
	 */
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return 0;
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.BaseDomain#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	private Opinion opinion;
}
