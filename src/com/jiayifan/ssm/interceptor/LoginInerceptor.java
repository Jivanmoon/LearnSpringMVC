package com.jiayifan.ssm.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * 登录认证的拦截器
 * @author 贾一帆
 *
 */
public class LoginInerceptor implements HandlerInterceptor {
	//执行Handler方法之后执行
	//统一的异常处理，统一的日志处理
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		
		
	}
	//进入Handler方法之后，返回ModelAndView之前执行
	//应用场景从modelAndView入手：将公用的模型数据（比如菜单的导航）在这里传到视图，也可以在这里统一指定视图
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
			throws Exception {
		
		
	}
	//进入Handler方法之前执行
	//用于身份认证、身份授权
	//比如身份认证，如果认证没有通过，需要此方法拦截
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		String url = request.getRequestURI();
		//判断url是否为公开地址，实际开发中配置在配置文件中
		//这里就是登录地址
		if(url.indexOf("login.action") >= 0) {
			//如果要执行登录，放行
			return true;
		}
		//判断Session
		HttpSession session = request.getSession();
		//从Session中取出用户身法信息
		String username = (String) session.getAttribute("username");
		if(username != null) return true;
		//执行到这里表示用户需要认证身份，跳转到登录页面
		request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request, response);
		//false表示拦截，true表示放行
		return false;
	}
	
}
