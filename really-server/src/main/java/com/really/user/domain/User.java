/**
 * @author zhangjx
 * @time 2014年7月13日 下午10:52:44
 * @description:
 */
package com.really.user.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.zhangjx.wx.persistence.BaseDomain;

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.CompareToBuilder;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午10:52:44
 *
 */
@Entity
@Table(name="T_REALLY_USER")
public class User extends BaseDomain<Long> implements Comparable<User> {
	
	private static final long serialVersionUID = 278359162092103711L;

	@Id@Column(name="PK")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Long id;
	
	@Column(name="MOBILE_PHONE")
	private String mobilePhone;
	
	@Column(name="USERNAME")
	private String username;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="EMAIL")
	private String email;
	
	@Column(name="LAST_LOGIN_TIME")
	private Date lastLoginTime;
	
	@Column(name="REGISTER_TIME")
	private Date registerTime;
	
	@Column(name="TRUTH_DEGREE_SCORE")
	private double truth_degree_score = 100D;
	
	public int compareTo(final User other) {
		return new CompareToBuilder().append(id, other.id)
				.append(mobilePhone, other.mobilePhone)
				.append(username, other.username)
				.append(password, other.password).append(email, other.email)
				.append(lastLoginTime, other.lastLoginTime)
				.append(registerTime, other.registerTime).toComparison();
	}

	@Override
	public boolean equals(final Object other) {
		if (!(other instanceof User))
			return false;
		User castOther = (User) other;
		return new EqualsBuilder().append(id, castOther.id)
				.append(mobilePhone, castOther.mobilePhone)
				.append(username, castOther.username)
				.append(password, castOther.password)
				.append(email, castOther.email)
				.append(lastLoginTime, castOther.lastLoginTime)
				.append(registerTime, castOther.registerTime).isEquals();
	}

	@Override
	public int hashCode() {
		return new HashCodeBuilder().append(id).append(mobilePhone)
				.append(username).append(password).append(email)
				.append(lastLoginTime).append(registerTime).toHashCode();
	}

	@Override
	public String toString() {
		return new ToStringBuilder(this).append("id", id)
				.append("mobilePhone", mobilePhone)
				.append("username", username).append("password", password)
				.append("email", email).append("lastLoginTime", lastLoginTime)
				.append("registerTime", registerTime).toString();
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

	public String getMobilePhone() {
		return mobilePhone;
	}

	public void setMobilePhone(String mobilePhone) {
		this.mobilePhone = mobilePhone;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(Date registerTime) {
		this.registerTime = registerTime;
	}

	public double getTruth_degree_score() {
		return truth_degree_score;
	}

	public void setTruth_degree_score(double truth_degree_score) {
		this.truth_degree_score = truth_degree_score;
	}
	
}
