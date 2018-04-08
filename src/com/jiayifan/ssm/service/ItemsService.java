package com.jiayifan.ssm.service;

import com.jiayifan.ssm.po.ItemsQueryVo;

import java.util.List;

import com.jiayifan.ssm.po.*;
/**
 * 商品管理的Service
 * @author 贾一帆
 *
 */
public interface ItemsService {
	//商品的查询列表
	public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
	//根据id查询商品信息
	public ItemsCustom findItemsById(Integer id) throws Exception;
	//修改商品信息
	/**
	 * 
	 * @param id 要修改商品的id
	 * @param itemsCustom
	 * @throws Exception
	 */
	public void updateItems(Integer id,ItemsCustom itemsCustom) throws Exception; 

}
