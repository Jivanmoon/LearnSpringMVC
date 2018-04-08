package com.jiayifan.ssm.po;

import java.util.List;

/**
 * 商品的包装对象
 * @author 贾一帆
 *
 */
public class ItemsQueryVo {
	//商品信息
	private Items items;
	//为了系统的可扩展性，对原始的po进行扩展
	private ItemsCustom itemsCustom;
	
	//批量商品信息
	private List<ItemsCustom> itemsList;
	
	public List<ItemsCustom> getItemsList() {
		return itemsList;
	}
	public void setItemsList(List<ItemsCustom> itemsList) {
		this.itemsList = itemsList;
	}
	public Items getItems() {
		return items;
	}
	public void setItems(Items items) {
		this.items = items;
	}
	public ItemsCustom getItemsCustom() {
		return itemsCustom;
	}
	public void setItemsCustom(ItemsCustom itemsCustom) {
		this.itemsCustom = itemsCustom;
	}
	
}
