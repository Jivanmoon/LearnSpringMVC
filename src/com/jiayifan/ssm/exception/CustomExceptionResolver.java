package com.jiayifan.ssm.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 全局异常处理器
 * @author 贾一帆
 *
 */
public class CustomExceptionResolver implements HandlerExceptionResolver {

	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler,
			Exception exception) {
		//handler就是处理器适配器要执行的Handler对象(只有method)
		//解析出异常类型
		//如果该 异常类型是系统 自定义的异常，直接取出异常信息，在错误页面展示
		CustomException customException = null;
		if(exception instanceof CustomException){
			customException = (CustomException)exception;
		}else{
			customException = new CustomException("未知错误");
		}
		//错误信息
		String message = customException.getMessage();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("message",message);
		modelAndView.setViewName("error");
		return modelAndView;
	}

}
