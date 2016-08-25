package com.douni.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.douni.model.User;
import com.douni.service.UserService;

@Controller
@RequestMapping("/user")
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/getUserInfo")
	@ResponseBody
	public JSONObject getUserInfo(HttpServletRequest request, HttpServletResponse response) {

		JSONObject result = new JSONObject();

		String userId = request.getParameter("userId");

		if (StringUtils.isBlank(userId)) {
			result.put("response", false);
			result.put("code", "1");
			result.put("message", "缺少参数");
			result.put("data", "");
			return result;
		}

		User user = userService.getUserInfo(Integer.valueOf(userId));
		if (user == null) {
			result.put("response", false);
			result.put("code", "2");
			result.put("message", "用户不存在");
			result.put("data", "");
			return result;
		}

		result.put("response", true);
		result.put("code", "0");
		result.put("message", "");
		result.put("data", user);

		return result;

	}

	@RequestMapping(value = "/register")
	@ResponseBody
	public JSONObject register(HttpServletRequest request, HttpServletResponse response) {

		JSONObject result = new JSONObject();

		String name = request.getParameter("name");
		String mobile = request.getParameter("mobile");
		String password = request.getParameter("password");

		if (StringUtils.isBlank(name) || StringUtils.isBlank(mobile) || StringUtils.isBlank(password)) {
			result.put("response", false);
			result.put("code", "1");
			result.put("message", "参数缺失");
			result.put("data", "");
			return result;
		}

		User user = new User();
		user.setName(name);
		user.setMobile(mobile);
		user.setPassword(password);

		int status = userService.register(user);
		if (status == -1) {
			result.put("response", false);
			result.put("code", "1");
			result.put("message", "缺少参数");
			result.put("data", "");
			return result;
		}else{
			result.put("response", true);
			result.put("code", "0");
			result.put("message", "");
			result.put("data", "");
		}

		return result;
	}

}
