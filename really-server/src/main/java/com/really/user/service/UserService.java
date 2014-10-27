/**
 * @author zhangjx
 * @time 2014年7月13日 下午11:10:51
 * @description:
 */
package com.really.user.service;

import java.util.Map;

import com.really.user.domain.User;
import com.zhangjx.wx.persistence.GenericService;

/**
 * @author zhangjx
 * @date 2014年7月13日 下午11:10:51
 *
 */
public interface UserService extends GenericService<User, Long> {

	User findByMoiblePhone(String mobilePhone);
	
	User addIfNotExist(String mobilePhone);
	
	User register(Map<String, Object> data);
	
	User findByUserName(String username);
	
	User login(Map<String, Object> data);
}
