/**
 * @author zhangjx
 * @time 2014年8月7日 上午11:47:20
 * @description:
 */
package com.really.user.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.really.exception.BusinessException;
import com.really.user.domain.User;
import com.really.user.service.UserService;

/**
 * @author zhangjx
 * @date 2014年8月7日 上午11:47:20
 *
 */
@Controller
@RequestMapping(value="/user")
public class UserController {
	
	@Resource
	UserService userService;

	@ResponseBody
	@RequestMapping(value="/register", method={RequestMethod.POST})
	public Map<String, Object> register(@RequestBody Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = this.userService.register(data);
			result.put("status", "0");
			result.put("userId", user.getId());
			result.put("lastLoginTime", user.getLastLoginTime());
		} catch (BusinessException e) {
			result.put("status", "1");
			result.put("error_code", e.getCode());
		} catch (Exception e) {
			result.put("status", "1");
			result.put("error_code", "998");
		}
		return result;
	}
	
	@ResponseBody
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public Map<String, Object> login(@RequestBody Map<String, Object> data) {
		Map<String, Object> result = new HashMap<String, Object>();
		try {
			User user = this.userService.login(data);
			result.put("status", "0");
			result.put("userId", user.getId());
			result.put("email", user.getEmail());
			result.put("lastLoginTime", new Date());
		} catch (BusinessException e) {
			result.put("status", "1");
			result.put("error_code", e.getCode());
		} catch (Exception e) {
			result.put("status", "1");
			result.put("error_code", "998");
		}
		return result;
	}
	
}
