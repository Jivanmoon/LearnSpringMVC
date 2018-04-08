package com.jiayifan.ssm.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.jiayifan.ssm.controller.validation.VaildGropOne;
import com.jiayifan.ssm.exception.CustomException;
import com.jiayifan.ssm.po.Items;
import com.jiayifan.ssm.po.ItemsCustom;
import com.jiayifan.ssm.po.ItemsQueryVo;
import com.jiayifan.ssm.service.ItemsService;


/**
 * 商品的Controller
 * @author 贾一帆
 *
 */
@Controller
//为了对我们的url进行分类管理，可以在这里定义一个根路径，最终访问url是根路径+子路径
//比如商品列表：/items/queryItems.action
@RequestMapping("/items")
public class ItemsController {
	//商品查询列表
	//@RequestMapping("/queryItems")实现的是对queryItems方法和url进行映射，一个方法对应一个url
	//建议url和方法名一样
	@Autowired
	private ItemsService itemsService;
	//商品分类
	//itemtypes表示最终将方法返回值放到requet域中的key
	@ModelAttribute("itemtypes")
	public Map<String, String> getItemTypes(){
		
		Map<String, String> itemTypes = new HashMap<String,String>();
		itemTypes.put("101", "数码");
		itemTypes.put("102", "母婴");
		
		return itemTypes;
	}
	@RequestMapping("/queryItems")
	public ModelAndView queryItems(ItemsQueryVo itemsQueryVo) throws Exception {
		//调用service查找数据库，查询商品列表，这里先使用静态的数据模拟
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute方法
		modelAndView.addObject("itemsList",itemsList);
		//指定视图
		modelAndView.setViewName("items/itemsList");
		return modelAndView;
	}
	//商品信息修改页面显示
	/*//@RequestMapping("/editItems")
	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	public ModelAndView editItems() throws Exception {
		//调用sercice查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(1);
		//返回ModelAndView
		ModelAndView modelAndView = new ModelAndView();
		//将商品信息放到Model
		modelAndView.addObject("itemsCustom",itemsCustom);
		//商品修改页面
		modelAndView.setViewName("items/editItems");
		return modelAndView;
	}*/
	@RequestMapping(value="/editItems",method={RequestMethod.POST,RequestMethod.GET})
	//@RequestParam中的value属性指定request传入的参数名称
	//通过required属性指定这个参数是否必须要传入
	//通过defaultValue属性可以设置默认值，如果id参数没有传入，就会将默认值和形参绑定
	public String editItems(Model model,@RequestParam(value="id",required=true,defaultValue="1") Integer items_id) throws Exception {
		//调用sercice查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(items_id);
		//判断商品是否为空
		/*if(itemsCustom == null) {
			throw new CustomException("controller中修改的商品信息不存在");
		}*/
		//通过参数中的model将数据传到页面
		model.addAttribute("itemsCustom",itemsCustom);
		return "items/editItems";
	}
	/*//商品信息修改提交
	@RequestMapping("/editItemsSubmit")
	public ModelAndView editItemsSubmit() throws Exception {
		//调用service返回商品信息，页面需要将商品信息传到此方法
		//此方法暂停
		
		ModelAndView modelAndView = new ModelAndView();
		//先返回一个成功页面
		modelAndView.setViewName("success");
		return modelAndView;
	}*/
	//商品信息修改提交
	//在需要校验的pojo前面添加@Validated注解,在后面添加BindingResult参数，来接收校验出错的信息
	//注意@Validated和BindingResult是配对出现的
	//value= {VaildGropOne.class}指定使用分组
	@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(Model model,Integer id,  
			@Validated(value= {VaildGropOne.class}) ItemsCustom itemsCustom, BindingResult bindingResult,
			@RequestParam("MultipartFile") MultipartFile items_pic//用来接收商品的图片
			) throws Exception {
		//获取校验的错误信息
		if(bindingResult.hasErrors()) {
			//输出错误信息
			List<ObjectError> allErrors = bindingResult.getAllErrors();
			for(ObjectError objectError : allErrors) {
				System.out.println(objectError.getDefaultMessage());
			}
			//将错误信息传到页面
			model.addAttribute("allErrors", allErrors);
			return "items/editItems";
		}
		//上传图片
		String originalFilename = items_pic.getOriginalFilename();
		if(items_pic != null && originalFilename != null && originalFilename.length() > 0) {
			//存储图片的物理路径
			String pic_path = "I:\\博客使用图片\\";
			//上传图片的原始名称
			//新的图片名称
			String newFileName = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
			//新的图片
			File newFile = new File(pic_path + newFileName);
			//将内存中的数据写入磁盘
			items_pic.transferTo(newFile);
			//将新的图片名称写到itemsCustom中
			itemsCustom.setPic(newFileName);
		}
		itemsService.updateItems(id, itemsCustom);
		//重定向
		return "success";
	}
	/*@RequestMapping("/editItemsSubmit")
	public String editItemsSubmit(HttpServletRequest request,Integer id,ItemsCustom itemsCustom) throws Exception {
		
		itemsService.updateItems(id, itemsCustom);
		//转发
		return "forward:queryItems.action";
	}*/
	
	@RequestMapping("/deleteItems") 
	public String deleteItems(Integer[] items_id) throws Exception {
		//调用service来批量删除商品
		return "success";
		
	}
	//批量修改商品页面，将商品信息查询出来，在页面中编辑商品信息
	@RequestMapping("/editItemsQuery")
	public ModelAndView editItemsQuery(ItemsQueryVo itemsQueryVo) throws Exception {
		//调用service查找数据库，查询商品列表，这里先使用静态的数据模拟
		List<ItemsCustom> itemsList = itemsService.findItemsList(itemsQueryVo);
		
		ModelAndView modelAndView = new ModelAndView();
		//相当于request的setAttribute方法
		modelAndView.addObject("itemsList",itemsList);
		//指定视图
		modelAndView.setViewName("items/editItemsQuery");
		return modelAndView;
	}
	//批量修改商品提交
	//通过ItemsQueryVo接收批量商品信息，将商品信息存储到itemsQueryVo中的itemsList中
	@RequestMapping("/editItemsAllSubmit")
	public String editItemsAllSubmit(ItemsQueryVo itemsQueryVo) throws Exception {
		
		
		return "success";
	}
	//查询商品信息，输出json
	///itemsView/{id}中的id表示这个位置的参数要传到@PathVariable指定的名称中
	@RequestMapping("/itemsView/{id}")
	public @ResponseBody ItemsCustom itemsView(@PathVariable("id") Integer id) throws Exception {
		//调用service查询商品信息
		ItemsCustom itemsCustom = itemsService.findItemsById(id);
		
		return itemsCustom;
		
	}
}

