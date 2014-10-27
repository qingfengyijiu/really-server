/**
 * @author zhangjx
 * @time 2014年9月3日 上午5:36:54
 * @description:
 */
package com.really.comment.hotspot.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.really.comment.domain.Comment;
import com.zhangjx.wx.persistence.BaseDomain;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author zhangjx
 * @date 2014年9月3日 上午5:36:54
 *
 */
@Entity
@Table(name="T_REALLY_COMMENT_HOTSPOT")
public class CommentHotspot extends BaseDomain<Long> implements Comparable<CommentHotspot> {
	
	private static final long serialVersionUID = -5528880922504750883L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private long id;
	
	@Column(name="RANK_DAY")
	private Date rankDay;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="COMMENT_PK")
	private Comment comment;
	
	@Column(name="HOTSPOT_VALUE")
	private double hotspotValue;

	public int compareTo(final CommentHotspot other) {
		return new CompareToBuilder().append(id, other.id)
				.append(rankDay, other.rankDay).append(comment, other.comment)
				.append(hotspotValue, other.hotspotValue).toComparison();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("rankDay", rankDay).append("comment", comment)
				.append("hotspotValue", hotspotValue).toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof CommentHotspot))
			return false;
		CommentHotspot castOther = (CommentHotspot) other;
		return new EqualsBuilder().append(id, castOther.id)
				.append(rankDay, castOther.rankDay)
				.append(comment, castOther.comment)
				.append(hotspotValue, castOther.hotspotValue).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(rankDay).append(comment)
				.append(hotspotValue).toHashCode();
	}

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
	
	public Date getRankDay() {
		return rankDay;
	}

	public void setRankDay(Date rankDay) {
		this.rankDay = rankDay;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public double getHotspotValue() {
		return hotspotValue;
	}

	public void setHotspotValue(double hotspotValue) {
		this.hotspotValue = hotspotValue;
	}

	public void setId(long id) {
		this.id = id;
	}

}
