package com.jiayifan.ssm.controller.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.convert.converter.Converter;

/**
 * 日期转换器
 * @author 贾一帆
 *
 */
public class CustomDateConverter implements Converter<String, Date> {

	@Override
	public Date convert(String source) {
		//实现将日期串转换为Date类型
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			//绑定成功，返回日期数据
			return simpleDateFormat.parse(source);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//如果参数绑定失败，返回空
		return null;
	}

}
