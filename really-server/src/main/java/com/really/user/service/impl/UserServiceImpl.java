/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:11:24
 * @description:
 */
package com.really.user.service.impl;

import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.really.exception.BusinessException;
import com.really.user.dao.UserDao;
import com.really.user.domain.User;
import com.really.user.service.UserService;
import com.zhangjx.wx.persistence.GenericDao;
import com.zhangjx.wx.persistence.QLQueryTemplate;
import com.zhangjx.wx.persistence.QueryTemplate;
import com.zhangjx.wx.persistence.impl.GenericServiceImpl;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:11:24
 *
 */
@Service("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserService {
	
	@Resource
	private UserDao dao;

	/* (non-Javadoc)
	 * @see com.zhangjx.wx.persistence.impl.GenericServiceImpl#getDao()
	 */
	@Override
	protected GenericDao<User, Long> getDao() {
		return dao;
	}

	/* (non-Javadoc)
	 * @see com.really.user.service.UserService#findByMoiblePhone(java.lang.String)
	 */
	@Override
	public User findByMoiblePhone(String mobilePhone) {
		QueryTemplate qt = new QLQueryTemplate("select u from User u where u.mobilePhone = :mobilePhone");
		qt.addParameter("mobilePhone", mobilePhone);
		return this.findUnique(qt);
	}

	/* (non-Javadoc)
	 * @see com.really.user.service.UserService#addIfNotExist(java.lang.String)
	 */
	@Override
	public User addIfNotExist(String mobilePhone) {
		User user = this.findByMoiblePhone(mobilePhone);
		if(null == user) {
			user = new User();
			user.setMobilePhone(mobilePhone);
			this.persist(user);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.really.user.service.UserService#register(java.util.Map)
	 */
	@Override
	public User register(Map<String, Object> data) {
		// 检查参数
		_checkRegister(data);
		// 获取参数
		String username = (String) data.get("username");
		String password = (String) data.get("password");
		String email = (String) data.get("email");
		// 查询用户名是否已经存在
		User old = this.findByUserName(username);
		if(old != null) {
			throw new BusinessException("004");
		}
		// 如果是新用户，则添加
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		Date now = new Date();
		user.setRegisterTime(now);
		user.setLastLoginTime(now);
		this.persist(user);
		return user;
	}
	
	private void _checkRegister(Map<String, Object> data) {
		try {
			String username = (String) data.get("username");
			String password = (String) data.get("password");
			String email = (String) data.get("email");
		} catch (Exception e) {
			throw new BusinessException("999");
		}
	}

	/* (non-Javadoc)
	 * @see com.really.user.service.UserService#findByUserName(java.lang.String)
	 */
	@Override
	public User findByUserName(String username) {
		QueryTemplate qt = new QLQueryTemplate("select u from User u where u.username = :username");
		qt.addParameter("username", username);
		return dao.findUnique(qt);
	}

	/* (non-Javadoc)
	 * @see com.really.user.service.UserService#login(java.util.Map)
	 */
	@Override
	public User login(Map<String, Object> data) {
		// 检查参数
		_checkLogin(data);
		// 获取参数
		String username = (String)data.get("username");
		String password = (String)data.get("password");
		User user = this.findByUserName(username);
		if(null == user) {
			throw new BusinessException("005");
		} else {
			String realPwd = user.getPassword();
			if(!password.equals(realPwd)) {
				throw new BusinessException("006");
			}
		}
		user.setLastLoginTime(new Date());
		this.persist(user);
		return user;
		
	}
	
	private void _checkLogin(Map<String, Object> data) {
		try {
			String username = (String) data.get("username");
			String password = (String) data.get("password");
		} catch (Exception e) {
			throw new BusinessException("999");
		}
	}

}
