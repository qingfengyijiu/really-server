/**
 * @author zhangjx
 * @time 2014年9月16日 上午9:58:29
 * @description:
 */
package com.really.test;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangjx
 * @date 2014年9月16日 上午9:58:29
 *
 */
@Controller
@RequestMapping(value="/test")
public class TestController {
	
	@RequestMapping(value="/login", method={RequestMethod.GET})
	public String forLogin() {
		return "test/login";
	}
	
	@RequestMapping(value="/login", method={RequestMethod.POST})
	public String login(HttpServletRequest request) {
		return "test/welcome";
	}
	
	@RequestMapping(value="/welcome", method={RequestMethod.GET})
	public String welcome() {
		return "test/welcome";
	}
	
	
}
