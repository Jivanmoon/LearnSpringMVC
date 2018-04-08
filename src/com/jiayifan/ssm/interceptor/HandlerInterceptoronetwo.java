package com.jiayifan.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 测试拦截器一
 * @author 贾一帆
 *
 */
public class HandlerInterceptoronetwo implements HandlerInterceptor {
	//执行Handler方法之后执行
	//统一的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("HandlerInterceptoronetwo......afterCompletion");
		
	}
	//进入Handler方法之后，返回ModelAndView之前执行
	//应用场景从modelAndView入手：将公用的模型数据（比如菜单的导航）在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		System.out.println("HandlerInterceptoronetwo......postHandle");
		
	}
	//进入Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证没有通过，需要此方法拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		System.out.println("HandlerInterceptoronetwo......preHandle");
		
		//false表示拦截，true表示放行
		return true;
	}
	
}
