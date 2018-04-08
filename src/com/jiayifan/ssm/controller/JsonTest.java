package com.jiayifan.ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.jiayifan.ssm.po.ItemsCustom;

/**
 * json交互测试
 * @author 贾一帆
 *
 */
@Controller
public class JsonTest {
	//请求json（商品信息），输出json（商品信息）
	//@RequestBody将请求的商品信息的对象转换为json串
	//@ResponseBody将返回的对象转换为json串
	@RequestMapping("/requestJson")
	public @ResponseBody ItemsCustom requestJson(@RequestBody ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	//请求key/value，输出json
	@RequestMapping("/responseJson")
	public @ResponseBody ItemsCustom responseJson(ItemsCustom itemsCustom) {
		return itemsCustom;
	}
	
}
