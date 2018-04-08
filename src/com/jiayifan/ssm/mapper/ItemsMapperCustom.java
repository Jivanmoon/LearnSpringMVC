package com.jiayifan.ssm.mapper;

import com.jiayifan.ssm.po.Items;
import com.jiayifan.ssm.po.ItemsCustom;
import com.jiayifan.ssm.po.ItemsExample;
import com.jiayifan.ssm.po.ItemsQueryVo;

import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface ItemsMapperCustom {
    public List<ItemsCustom> findItemsList(ItemsQueryVo itemsQueryVo) throws Exception;
}