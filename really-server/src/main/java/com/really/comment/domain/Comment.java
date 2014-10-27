/**
 * @author zhangjx
 * @time 2014年7月13日 下午7:48:25
 * @description:
 */
package com.really.comment.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.really.news.domain.News;
import com.really.user.domain.User;
import com.zhangjx.wx.persistence.BaseDomain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午7:48:25
 *
 */
@Entity
@Table(name="T_REALLY_COMMENT")
public class Comment extends BaseDomain<Long> implements Comparable<Comment> {
	
	private static final long serialVersionUID = 2894524235000988126L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="NEWS_ID")
	private News news;
	
	@Column(name="CONTENT")
	private String content;
	
	@ManyToOne(optional=true)
	@JoinColumn(name="CREATOR")
	private User creator;
	
	@Column(name="CREATE_TIME")
	private Date createTime;
	
	@Column(name="BACKGROUND_RESOURCE_ID")
	private int background;
	
	@Column(name="BACKGROUND_ALPHA")
	private int alpha;
	
	@Column(name="HOTSPOT_VALUE")
	private double hotspotValue = 0D;
	
	@Column(name="ATTENTION")
	private long attention = 0L;
	
	public int compareTo(final Comment other) {
		return new CompareToBuilder().append(id, other.id)
				.append(news, other.news).append(content, other.content)
				.append(creator, other.creator)
				.append(createTime, other.createTime)
				.append(background, other.background)
				.append(alpha, other.alpha).toComparison();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id).append("news", news)
				.append("content", content).append("creator", creator)
				.append("createTime", createTime)
				.append("background", background).append("alpha", alpha)
				.toString();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof Comment))
			return false;
		Comment castOther = (Comment) other;
		return new EqualsBuilder().append(id, castOther.id)
				.append(news, castOther.news)
				.append(content, castOther.content)
				.append(creator, castOther.creator)
				.append(createTime, castOther.createTime)
				.append(background, castOther.background)
				.append(alpha, castOther.alpha).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(news).append(content)
				.append(creator).append(createTime).append(background)
				.append(alpha).toHashCode();
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

	public News getNews() {
		return news;
	}

	public void setNews(News news) {
		this.news = news;
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

	public int getBackground() {
		return background;
	}

	public void setBackground(int background) {
		this.background = background;
	}

	public int getAlpha() {
		return alpha;
	}

	public void setAlpha(int alpha) {
		this.alpha = alpha;
	}

	public double getHotspotValue() {
		return hotspotValue;
	}

	public void setHotspotValue(double hotspotValue) {
		this.hotspotValue = hotspotValue;
	}

	public long getAttention() {
		return attention;
	}

	public void setAttention(long attention) {
		this.attention = attention;
	}
	
}
