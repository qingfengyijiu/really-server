/**
 * @author zhangjx
 * @time 2014年7月13日 下午7:41:52
 * @description:
 */
package com.really.news.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.really.user.domain.User;
import com.zhangjx.wx.persistence.BaseDomain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午7:41:52
 *
 */
@Entity
@Table(name="T_REALLY_NEWS")
public class News extends BaseDomain<Long> implements Comparable<News> {
	
	private static final long serialVersionUID = 997246786589068977L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="URL")
	private String url;
	
	@Column(name="TITLE")
	private String title;
	
	@Column(name="CONTENT")
	private String content;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CREATOR_ID")
	private User creator;
	
	@Column(name="TRUTH_DEGREE")
	private Double truthDegree = 0D;
	
	@Column(name="OPINION_TIMES")
	private long opinionTimes = 0L;
	
	public int compareTo(final News other) {
		return new CompareToBuilder().append(id, other.id)
				.append(url, other.url).append(title, other.title)
				.append(content, other.content)
				.append(createTime, other.createTime)
				.append(creator, other.creator).toComparison();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("url", url)
				.append("title", title).append("content", content)
				.append("createTime", createTime).append("creator", creator)
				.toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof News))
			return false;
		News castOther = (News) other;
		return new EqualsBuilder().append(id, castOther.id)
				.append(url, castOther.url).append(title, castOther.title)
				.append(content, castOther.content)
				.append(createTime, castOther.createTime)
				.append(creator, castOther.creator).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(url).append(title)
				.append(content).append(createTime).append(creator)
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public User getCreator() {
		return creator;
	}

	public void setCreator(User creator) {
		this.creator = creator;
	}

	public Double getTruthDegree() {
		return truthDegree;
	}

	public void setTruthDegree(Double truthDegree) {
		this.truthDegree = truthDegree;
	}

	public long getOpinionTimes() {
		return opinionTimes;
	}

	public void setOpinionTimes(long opinionTimes) {
		this.opinionTimes = opinionTimes;
	}
	
}
