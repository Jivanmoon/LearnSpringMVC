package com.jiayifan.ssm.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {
	//登录
	@RequestMapping("/login")
	public String login(HttpSession session, String username, String password) throws Exception {
		//调用service进行登录验证
		//代码省略
		
		//在Session中保存用户信息
		session.setAttribute("username", username);
		//重定向到商品列表页面
		return "redirect:items/queryItems.action";
	}
	//退出
	@RequestMapping("/logout")
	public String logout(HttpSession session) throws Exception {
		//清楚Session
		session.invalidate();
		//重定向到商品列表页面
		return "redirect:items/queryItems.action";
	}
}
