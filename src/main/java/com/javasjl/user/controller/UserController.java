package com.javasjl.user.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.javasjl.user.entity.User;
import com.javasjl.user.service.UserService;

/**
 * 用户controller层
 * @author stanshi
 *
 */
@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户登录
	 * @param user
	 * @param request
	 * @return
	 */
	@RequestMapping("/login")
	public String login(User user, HttpServletRequest request) {
		User resultUser = userService.login(user);
		if (resultUser == null) {
			request.setAttribute("user", user);
			request.setAttribute("errorMsg", "用户名或密码错误");
			return "index";
		}else {
			HttpSession session = request.getSession(); 
			session.setAttribute("currentUser", resultUser);
			return "redirect:/success.jsp";
		}
	}

}
