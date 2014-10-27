/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:01:01
 * @description:
 */
package com.really.opinion.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.really.comment.domain.Comment;
import com.really.user.domain.User;
import com.zhangjx.wx.persistence.BaseDomain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:01:01
 *
 */
@Entity
@Table(name="T_REALLY_OPINION")
public class Opinion extends BaseDomain<Long> implements Comparable<Opinion> {
	
	private static final long serialVersionUID = -8667376368465917419L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="TRUTH_DEGREE")
	private int truthDegree;
	
	@Column(name="RELATIONSHIP")
	private int relationship;
	
	@Column(name="CONTENT")
	private String content;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CREATOR")
	private User creator;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="COMMENT_ID")
	private Comment comment;
	

	public int compareTo(final Opinion other) {
		return new CompareToBuilder().append(id, other.id)
				.append(truthDegree, other.truthDegree)
				.append(relationship, other.relationship)
				.append(content, other.content).append(creator, other.creator)
				.toComparison();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("truthDegree", truthDegree)
				.append("relationship", relationship)
				.append("content", content).append("creator", creator)
				.toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Opinion))
			return false;
		Opinion castOther = (Opinion) other;
		return new EqualsBuilder().append(id, castOther.id)
				.append(truthDegree, castOther.truthDegree)
				.append(relationship, castOther.relationship)
				.append(content, castOther.content)
				.append(creator, castOther.creator).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(truthDegree)
				.append(relationship).append(content).append(creator)
				.toHashCode();
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.Identifiable#getId()
	 */
	@Override
	public Long getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.Identifiable#setId(java.io.Serializable)
	 */
	@Override
	public void setId(Long id) {
		this.id = id;
	}


	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Comment getComment() {
		return comment;
	}

	public void setComment(Comment comment) {
		this.comment = comment;
	}

	public int getTruthDegree() {
		return truthDegree;
	}

	public void setTruthDegree(int truthDegree) {
		this.truthDegree = truthDegree;
	}

	public int getRelationship() {
		return relationship;
	}

	public void setRelationship(int relationship) {
		this.relationship = relationship;
	}
	
}
