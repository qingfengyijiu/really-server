/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:09:37
 * @description:
 */
package com.really.user.dao.impl;

import org.springframework.stereotype.Repository;

import com.really.user.dao.UserDao;
import com.really.user.domain.User;
import com.zhangjx.wx.persistence.impl.GenericDaoImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:09:37
 *
 */
@Repository("userDao")
public class UserDaoImpl extends GenericDaoImpl<User, Long> implements UserDao {

	/**
	 * @param persistentClass
	 */
	public UserDaoImpl() {
		super(User.class);
	}

}
