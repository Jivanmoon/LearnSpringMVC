package com.jiayifan.ssm.service.impl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

import com.jiayifan.ssm.exception.CustomException;
import com.jiayifan.ssm.mapper.ItemsMapper;
import com.jiayifan.ssm.mapper.ItemsMapperCustom;
import com.jiayifan.ssm.po.Items;
import com.jiayifan.ssm.po.ItemsCustom;
import com.jiayifan.ssm.po.ItemsQueryVo;
import com.jiayifan.ssm.service.ItemsService;

/**
 * 商品管理
 * @author 贾一帆
 *
 */
public class ItemsServiceImpl implements ItemsService {
	@Autowired
	private ItemsMapperCustom itemsMapperCustom;
	@Autowired
	private ItemsMapper itemsMapper;
	@Override
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception {
		//通过ItemsMapperCustom查询数据库
		return itemsMapperCustom.findItemsList(itemsQueryVo);
	}
	@Override
	public ItemsCustom findItemsById(Integer id) throws Exception {
		Items items = itemsMapper.selectByPrimaryKey(id);
		if(items == null) {
			throw new CustomException("service中修改的商品信息不存在");
		}
		//中间对商品信息进行业务处理
		//业务处理代码
		//返回扩展类
		ItemsCustom itemsCustom = null;
		//将items的内容拷贝到itemsCustom中
		if(items != null) {
			itemsCustom = new ItemsCustom();
			BeanUtils.copyProperties(items, itemsCustom);
		}
			
		return itemsCustom;
	}
	@Override
	public void updateItems(Integer id, ItemsCustom itemsCustom) throws Exception {
		//添加业务校验，通常在service接口对关键参数进行校验
		//校验id是否为空，如果为空我们就抛出异常
		
		//更新商品信息，使用该方法可以根据id修改items表中的所有信息，包括大文本字段
		//调用updateByPrimaryKeyWithBLOBs方法必须在参数对象中传入id
		itemsCustom.setId(id);
		itemsMapper.updateByPrimaryKeyWithBLOBs(itemsCustom);
	}
}
